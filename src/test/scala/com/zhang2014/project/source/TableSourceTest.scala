//package com.zhang2014.project.source
//
//import java.io.File
//import java.util.Date
//
//import akka.actor.ActorSystem
//import akka.stream.ActorMaterializer
//import akka.stream.scaladsl.Keep
//import akka.stream.testkit.scaladsl.TestSink
//import com.zhang2014.project.source.DataPartSource.Record
//import org.scalatest.WordSpec
//
//import scala.concurrent.duration._
//
//class TableSourceTest extends WordSpec
//{
//  implicit val system       = ActorSystem()
//  implicit val materializer = ActorMaterializer()
//
//  "TableSource" should {
//
//    //  test_table_1:
//
//    //  ┌──eventDate─┬─eventId─┬─eventName─┬─count─┐
//    //  │ 1980-01-01 │       1 │ OnClick   │     3 │
//    //  └────────────┴─────────┴───────────┴───────┘
//    //  ┌──eventDate─┬─eventId─┬─eventName─┬─count─┐
//    //  │ 0000-00-00 │       1 │ OnClick   │     3 │
//    //  └────────────┴─────────┴───────────┴───────┘
//
//    "successfully read table data" in {
//      val tableURI = getClass.getClassLoader.getResource("test_table_1").toURI
//      val tableSource = TableSource(new File(tableURI).getAbsolutePath)
//      val sub = tableSource.toMat(TestSink.probe[Record])(Keep.right).run()
//      sub.request(3)
//      sub.expectNext(
//        3 seconds, new Record(
//          ("Date", "eventDate", new Date(0)) ::
//            ("UInt64", "eventId", 1L) ::
//            ("String", "eventName", "OnClick") ::
//            ("Int32", "count", 3) ::
//            Nil
//        )
//      )
//      sub.expectNext(
//        3 seconds, new Record(
//          ("Date", "eventDate", new Date(80, 0, 1, 8, 0, 0)) ::
//            ("UInt64", "eventId", 1L) ::
//            ("String", "eventName", "OnClick") ::
//            ("Int32", "count", 3) ::
//            Nil
//        )
//      )
//      sub.expectComplete()
//    }
//  }
//}
