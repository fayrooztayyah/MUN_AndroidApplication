package org.Nablus.eservices.Models


data class ComplaintModel (
    var Complaint_ID : Int
    ,var Complaint_Address : String
    ,var Longitude : String
    ,var Latitude : String
    ,var Complaint_Desc : String
    ,var IS_Sent : Boolean
){}
