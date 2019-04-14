package org.Nablus.eservices.Activities

import android.app.Activity
import android.app.AlertDialog
import android.app.PendingIntent.getActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import kotlinx.android.synthetic.main.activity_add_complaint.*

import com.google.android.gms.common.api.GoogleApiClient

import com.google.android.gms.location.places.Places

import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.location.places.Place
import android.content.Intent
import android.util.Log
import android.provider.MediaStore
import android.content.DialogInterface
import android.net.Uri
import android.os.Environment
import java.io.File
import android.widget.Toast
import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.R.attr.bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import com.google.android.gms.internal.f







class AddComplaintActivity : AppCompatActivity() {

    private val PLACE_PICKER_REQUEST=1
    private var mClient: GoogleApiClient?=null

    protected val CAMERA_REQUEST=0
    protected val GALLERY_PICTURE=1
    private val pictureActionIntent: Intent?=null
    var selectedImagePath: String?=null
    var bitmap: Bitmap?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(org.Nablus.eservices.R.layout.activity_add_complaint)


    }
    fun imComplaintImage1_Click(view:View) {
         startDialog()

    }

    private fun startDialog() {
        val myAlertDialog=AlertDialog.Builder(
           this
        )
        myAlertDialog.setTitle(" Pictures Option")
        myAlertDialog.setMessage("How do you want to set your picture?")

        myAlertDialog.setPositiveButton("Gallery",
            DialogInterface.OnClickListener { arg0, arg1 ->
                var pictureActionIntent: Intent?=null

                pictureActionIntent=Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(
                    pictureActionIntent,
                    GALLERY_PICTURE
                )
            })

        myAlertDialog.setNegativeButton("Camera",
            DialogInterface.OnClickListener { arg0, arg1 ->
                val intent=Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE
                )
                val f=File(
                    Environment
                        .getExternalStorageDirectory(), "temp.jpg"
                )
                intent.putExtra(
                    MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(f)
                )

                startActivityForResult(
                    intent,
                    CAMERA_REQUEST
                )
            })
        myAlertDialog.show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)



        bitmap = null;
        selectedImagePath = null;

        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST) {

            var f=File(
                Environment.getExternalStorageDirectory()
                    .toString()
            )
            for (temp in f.listFiles()!!) {
                if (temp.name == "temp.jpg") {
                    f=temp
                    break
                }
            }

            if (!f.exists()) {

                Toast.makeText(
                    baseContext,

                    "Error while capturing image", Toast.LENGTH_LONG
                )

                    .show()

                return

            }

            bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());

            bitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, true);

            var rotate=0
            try {
                val exif=ExifInterface(f.absolutePath)
                val orientation=exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL
                )

                when (orientation) {
                    ExifInterface.ORIENTATION_ROTATE_270 -> rotate=270
                    ExifInterface.ORIENTATION_ROTATE_180 -> rotate=180
                    ExifInterface.ORIENTATION_ROTATE_90 -> rotate=90
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val matrix=Matrix()
            //matrix.postRotate(rotate)

//            bitmap=Bitmap.createBitmap(
//                bitmap, 0, 0, bitmap.getWidth(),
//                bitmap.getHeight(), matrix, true
//            )

            imComplaintImage1.setImageBitmap(bitmap);

        }


        else if (resultCode == Activity.RESULT_OK && requestCode == GALLERY_PICTURE) {
            if (data != null) {

                val selectedImage=data.data
                val filePath=arrayOf(MediaStore.Images.Media.DATA)
                val c=contentResolver.query(selectedImage!!, filePath, null, null, null)
                c!!.moveToFirst()
                val columnIndex=c.getColumnIndex(filePath[0])
                selectedImagePath=c.getString(columnIndex)
                c.close()



                bitmap=(BitmapFactory.decodeFile(selectedImagePath)) // load
                // preview image
                bitmap= Bitmap.createScaledBitmap(bitmap, 400, 400, false)



                imComplaintImage1.setImageBitmap(bitmap)

            } else {
                Toast.makeText(
                    applicationContext, "Cancelled",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }







    fun btnSubmitComplaint_Click(view: View) {

        var complaintDetails=edComplaint_Details.text.toString()
        var userAddress=edUser_Address.text.toString()

        val builder=PlacePicker.IntentBuilder()
        val intent: Intent
        try {
            intent=builder.build(this)
            startActivityForResult(intent, PLACE_PICKER_REQUEST)
            println("start activity for result")
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }


    }

//    override fun onStart() {
//        super.onStart()
//        mClient?.connect()
//    }
//
//    override fun onStop() {
//      //  mClient?.disconnect()
//        super.onStop()
//    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//
//        Log.d("resultCode",resultCode.toString())
//        if (requestCode == PLACE_PICKER_REQUEST) {
//            if (resultCode == Activity.RESULT_OK) {
//                val place=PlacePicker.getPlace(data!!, this)
//                val stBuilder=StringBuilder()
//                val placename=String.format("%s", place.name)
//                val latitude=place.latLng.latitude.toString()
//                val longitude=place.latLng.longitude.toString()
//                val address=String.format("%s", place.address)
//                stBuilder.append("Name: ")
//                stBuilder.append(placename)
//                stBuilder.append("\n")
//                stBuilder.append("Latitude: ")
//                stBuilder.append(latitude)
//                stBuilder.append("\n")
//                stBuilder.append("Logitude: ")
//                stBuilder.append(longitude)
//                stBuilder.append("\n")
//                stBuilder.append("Address: ")
//                stBuilder.append(address)
//
//             // Log.d("stBuilder",stBuilder.toString())
//              //  textView.setText(stBuilder.toString())
//            }
//        }
//    }
}
