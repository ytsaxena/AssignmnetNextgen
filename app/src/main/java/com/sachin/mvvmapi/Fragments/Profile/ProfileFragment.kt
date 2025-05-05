package com.sachin.mvvmapi.Fragments.Profile

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import com.google.android.gms.location.LocationRequest
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.sachin.mvvmapi.R
import com.sachin.mvvmapi.Utility.SessionManager
import com.sachin.mvvmapi.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import com.google.android.gms.location.Priority
import java.io.File


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    // private val args: ProfileFragmentArgs by navArgs()
    private lateinit var sessionManager: SessionManager

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest


    // Permission launcher
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true || permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
            // Permission granted
            getLocation()
        } else {
            // Permission denied
            Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT)
                .show()
            binding.latlongtextview.text = "Permission denied"
        }
    }



    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            updateProfileImage(it)
        }
    }

    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        bitmap?.let {
            // Save image to a file or convert to Uri
            val uri = saveBitmapToCacheAndGetUri(it)
            updateProfileImage(uri)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false)

        binding = FragmentProfileBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val user = args.USER
//        val name = args.NAME
//        val email = args.EMAIL
//        val photo = args.PROFILE

        // Initialize location services
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        createLocationRequest()
        createLocationCallback()



        sessionManager = SessionManager(requireContext())

        val userId = sessionManager.getUserId()
        val name = sessionManager.getName()
        val email = sessionManager.getEmail()
        val photoUrl = sessionManager.getPhotoUrl()

        val photoUri = photoUrl?.let { Uri.parse(it) }

        binding.nameTextView.text = name
        binding.emailTextView.text = email
        binding.userIdTextView.text = userId

        Glide.with(this).load(photoUri).placeholder(R.drawable.baseline_person_pin_24)
            .into(binding.profileImageView)


        binding.channgePrfpicture.setOnClickListener {
            showImagePickerDialog()


        }


        checkPermissionAndGetLocation()


    }

    private fun showImagePickerDialog() {
        val options = arrayOf("Camera", "Gallery")

        AlertDialog.Builder(requireContext())
            .setTitle("Select Profile Picture")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> cameraLauncher.launch()         // Camera
                    1 -> galleryLauncher.launch("image/*") // Gallery
                }
            }
            .show()
    }

    private fun saveBitmapToCacheAndGetUri(bitmap: Bitmap): Uri {
        val file = File(requireContext().cacheDir, "profile_${System.currentTimeMillis()}.png")
        file.outputStream().use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.flush()
        }
        return FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            file
        )
    }


    private fun updateProfileImage(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .placeholder(R.drawable.baseline_person_pin_24)
            .into(binding.profileImageView)

        sessionManager.saveUserData(
            uid = sessionManager.getUserId() ?: "",
            name = sessionManager.getName(),
            email = sessionManager.getEmail(),
            photoUrl = uri.toString()
        )
    }



    private fun createLocationRequest() {
        locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(2000)
            .setMaxUpdateDelayMillis(10000)
            .build()
    }



    private fun createLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    // Update UI with new location

                    binding.latlongtextview.text = "Lat: ${location.latitude}, Lng: ${location.longitude}"

                }
            }
        }
    }

    private fun checkPermissionAndGetLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permissions
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
            return
        }

        // Permission already granted, get location
        startLocationUpdates()

        // Also get last location for immediate display
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    binding.latlongtextview.text = "Lat: ${location.latitude}, Lng: ${location.longitude}"
                }
            }.addOnFailureListener { e ->
                Toast.makeText(
                    requireContext(),
                    "Error getting last location: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        binding.latlongtextview.text = "Searching..."


        fusedLocationClient.requestLocationUpdates(
            locationRequest, locationCallback, Looper.getMainLooper()
        )
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    override fun onResume() {
        super.onResume()
        if (::fusedLocationClient.isInitialized) {
            checkPermissionAndGetLocation()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (::fusedLocationClient.isInitialized) {
            stopLocationUpdates()
        }

    }


}
