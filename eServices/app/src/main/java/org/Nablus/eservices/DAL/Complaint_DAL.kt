package org.Nablus.eservices.DAL

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import org.Nablus.eservices.Models.ComplaintModel
class Complaint_DAL (var db: SQLiteDatabase) {
    val TABLENAME :String = "Complaint"
    var COMPLAINT_ID : String = "Complaint_ID"
    var COMPLAINT_ADDRESS : String = "Complaint_Address"
    var LONGITUDE : String = "Longitude"
    var LATITUDE : String = "Latitude"
    var COMPLAINT_DESC : String = "Complaint_Desc"
    var IS_SENT : String = "IS_Sent"
//     Create Table



    public fun addComplaint(db: SQLiteDatabase, complaintModel: ComplaintModel): Boolean {
        val cv = ContentValues()
        cv.put( COMPLAINT_ID , complaintModel.Complaint_ID)
        cv.put( COMPLAINT_ADDRESS , complaintModel.Complaint_Address)
        cv.put( LONGITUDE , complaintModel.Longitude)
        cv.put( LATITUDE , complaintModel.Latitude)
        cv.put( COMPLAINT_DESC , complaintModel.Complaint_Desc)
        cv.put( IS_SENT , complaintModel.IS_Sent)
        val result = db.insert(TABLENAME, null, cv)
        return !result.equals(-1)
    }
    public fun updateComplaint(Complaint_ID : Int, ComplaintModel: ComplaintModel): Boolean {
        var cv = ContentValues()
        cv.put(COMPLAINT_ADDRESS, ComplaintModel.Complaint_Address)
        cv.put(LONGITUDE, ComplaintModel.Longitude)
        cv.put(LATITUDE, ComplaintModel.Latitude)
        cv.put(COMPLAINT_DESC, ComplaintModel.Complaint_Desc)
        cv.put(IS_SENT, ComplaintModel.IS_Sent)
        db.update(TABLENAME, cv, "$COMPLAINT_ID=?", arrayOf(Complaint_ID.toString()))
        return true
    }
    public fun deleteComplaint(Complaint_ID:Int): Boolean {
        db.delete(TABLENAME, "$COMPLAINT_ID=?", arrayOf(Complaint_ID.toString()))
        return true
    }
    public fun getComplaint(Complaint_ID: Int): ComplaintModel {
        lateinit var complaintModel: ComplaintModel
        var Cursor: Cursor
        val strSQL: String = "Select * from $TABLENAME where $COMPLAINT_ID=$Complaint_ID"
        Cursor = db.rawQuery(strSQL, null)
        if (Cursor.moveToFirst()) {
            var intComplaint_ID = Cursor.getInt(Cursor.getColumnIndex(COMPLAINT_ID))
            var strComplaint_Address = Cursor.getString(Cursor.getColumnIndex(COMPLAINT_ADDRESS))
            var strLongitude = Cursor.getString(Cursor.getColumnIndex(LONGITUDE))
            var strLatitude = Cursor.getString(Cursor.getColumnIndex(LATITUDE))
            var strComplaint_Desc = Cursor.getString(Cursor.getColumnIndex(COMPLAINT_DESC))
            var boolIS_Sent = Cursor.getInt(Cursor.getColumnIndex(IS_SENT)) > 0
            complaintModel = ComplaintModel(
                Complaint_ID = intComplaint_ID
                ,Complaint_Address = strComplaint_Address
                ,Longitude = strLongitude
                ,Latitude = strLatitude
                ,Complaint_Desc = strComplaint_Desc
                ,IS_Sent = boolIS_Sent
            )
        }
        return complaintModel
    }
    public fun GetComplaintList() : ArrayList<ComplaintModel> {
        val strSQL: String = " Select * from $TABLENAME"
        val cursor: Cursor = db.rawQuery(strSQL, null)
        var complaintModel: ComplaintModel
        var ComplaintList = ArrayList<ComplaintModel>()
        if (cursor.count == 0){
        } else {
            val buffer = StringBuffer()
            var intComplaint_ID: Int
            var strComplaint_Address: String
            var strLongitude: String
            var strLatitude: String
            var strComplaint_Desc: String
            var boolIS_Sent: Boolean
            while (cursor.moveToNext()) {
                intComplaint_ID = cursor.getInt(cursor.getColumnIndex(COMPLAINT_ID))
                strComplaint_Address = cursor.getString(cursor.getColumnIndex(COMPLAINT_ADDRESS))
                strLongitude = cursor.getString(cursor.getColumnIndex(LONGITUDE))
                strLatitude = cursor.getString(cursor.getColumnIndex(LATITUDE))
                strComplaint_Desc = cursor.getString(cursor.getColumnIndex(COMPLAINT_DESC))
                boolIS_Sent = cursor.getInt(cursor.getColumnIndex(IS_SENT)) > 0
                complaintModel = ComplaintModel(
                    Complaint_ID = intComplaint_ID
                    ,Complaint_Address = strComplaint_Address
                    ,Longitude = strLongitude
                    ,Latitude = strLatitude
                    ,Complaint_Desc = strComplaint_Desc
                    ,IS_Sent = boolIS_Sent
                )
                ComplaintList.add(complaintModel)
            }
        }
        return ComplaintList
    }
}
