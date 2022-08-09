// JSON 데이터를 담을 클래스 생성
package com.example.tour.api

import com.google.gson.annotations.SerializedName

//data class VaccineBody(
//    @SerializedName("UC_SEQ") val UC_SEQ:Int
//){
//    override fun toString(): String {
//      return "UC_SEQ : $UC_SEQ\n"
//    }
//}
data class VaccineBody(
    @SerializedName("getFestivalKr") val getFestivalKr:List<Vaccine1>
){
    override fun toString(): String {
        return "getFestivalKr : $getFestivalKr\n"
    }
}
data class Vaccine1(
    @SerializedName("item") val item: List<Vaccine2>,
    @SerializedName("numOfRows") val numOfRows: Int
){
    override fun toString(): String {
        return "item : $item\n" +
                "numOfRows : $numOfRows"
    }
}
data class Vaccine2(
    @SerializedName("UC_SEQ") val UC_SEQ: Int,
    @SerializedName("MAIN_TITLE") val MAIN_TITLE: String
){
    override fun toString(): String {
        return "UC_SEQ : ${UC_SEQ}\n" +
                "MAIN_TITLE : $MAIN_TITLE"
    }
}

// data class VaccineBody(
//    @SerializedName("currentCount") val currentCount:Int,   // 현재 검색된 데이터 수
//    @SerializedName("data") val data:List<Vaccine>,         // 백신 현황 데이터
//    @SerializedName("matchCount") val matchCount:Int,       // 검색과 일치하는 데이터 수
//    @SerializedName("page") val page:Int,                   // 데이터 페이지
//    @SerializedName("perPage") val perPage:Int,             // 한번에 불러올 데이터
//    @SerializedName("totalCount") val totalCount:Int        // 데이터 전체 개수
//){
//    override fun toString(): String {
//        return "$data\n\n" +
//                "currentCount : $currentCount\n" +
//                "matchCount : $matchCount\n" +
//                "page : $page\n" +
//                "perPage : $perPage\n" +
//                "totalCount : $totalCount"
//    }
//}
//
//data class Vaccine(
//    @SerializedName("accumulatedFirstCnt") val accumulatedFirstCnt:Int,     // 전일까지의 누적 통계 1차
//    @SerializedName("accumulatedSecondCnt") val accumulatedSecondCnt:Int,   // 전일까지의 누적 통계 2차
//    @SerializedName("baseDate") val baseDate:String,                        // 통계 기준일자
//    @SerializedName("firstCnt") val firstCnt:Int,                           // 당일 통계 1차
//    @SerializedName("secondCnt") val secondCnt:Int,                         // 당일 통계 2차
//    @SerializedName("sido") val area:String,                                // 지역명칭
//    @SerializedName("totalFirstCnt") val totalFirstCnt:Int,                 // 전체 누적 통계 1차
//    @SerializedName("totalSecondCnt") val totalSecondCnt:Int                // 전체 누적 통계 2차
//){
//    override fun toString(): String {
//        return "Vaccine : [\n" +
//                "    accumulatedFirstCnt : ${accumulatedFirstCnt}\n" +
//                "    accumulatedSecondCnt : ${accumulatedSecondCnt}\n" +
//                "    baseDate : ${baseDate}\n" +
//                "    firstCnt : ${firstCnt}\n" +
//                "    secondCnt : ${secondCnt}\n" +
//                "    area : ${area}\n" +
//                "    totalFirstCnt : ${totalFirstCnt}\n" +
//                "    totalSecondCnt : ${totalSecondCnt}]\n\n"
//    }
//}