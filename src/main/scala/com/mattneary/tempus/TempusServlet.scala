package com.mattneary.tempus

import com.mattneary.schedule._;
import collection.mutable.HashMap

import org.scalatra._
import scalate.ScalateSupport
import scalaj.http.{Http, HttpOptions}

import scala.io.Source
import java.io._
import org.slf4j.{Logger, LoggerFactory}
import java.nio._
import net.liftweb.json._
import java.io.{File,FileInputStream,FileOutputStream}

class TempusServlet extends TempusStack {
  val dest = "/Users/mattneary/Desktop/School/Honors Java/Schedules/TempusServer/"
  val file = "index.html"
  def renderPage(file: String) {
    org.scalatra.util.io.copy(new FileInputStream(file), response.getOutputStream)
  }
  def parseNum(s: String) : Int = s.toInt
  def structure(offset: Int, lunch: Int) : Array[Block] = {
    val offsets = HashMap( 0 -> 2, 1 -> 4, 2 -> 6, 3 -> 8, 4 -> 11, 5 -> 13, 6 -> 15, 7 -> 18, 8 -> 20 )
    val hr = new Block(7, false)
    val n = new Block(32, true)
    val l = new Block(lunch, false)
    val s = new Block(5, false)
    val a = new Block(2, false)
    val day = Array(hr, s, n, s, n, s, n, s, n, a, s, l, s, n, s, n, a, s, n)
    day.slice(offsets(offset), 20)
  }
  def calc(structure : Array[Block], duration: Float) : Array[java.lang.Float] = {
    val sched = new Schedule()
    val breaks = Array[Break]()
    sched.reshape(structure, breaks, duration)
  }
  get("/") {
    renderPage(dest + file)
  }
  get("/asset/*") {
    val rest = multiParams("splat")
    val file = rest.mkString("/")
    renderPage(dest + file)
  }
  post("/submit") {
    contentType = "text/plaintext"
    val start = request.getParameter("start")
    val comps = start.split(":").map(parseNum)
    val endComps = request.getParameter("end").split(":").map(parseNum)
    val minStart = comps(0)*60 + comps(1)
    val minEnd = endComps(0)*60 + endComps(1)
    val period = parseNum(request.getParameter("period"))
    val lunch = parseNum(request.getParameter("lunch"))
    val calced = JArray(calc(structure(period, lunch), minEnd - minStart).toArray.toList.map(_.toDouble).map(JDouble))
    val times = compact(JsonAST.render(calced))
    response.getOutputStream.print(times)
    response.getOutputStream.close()
  }
}
