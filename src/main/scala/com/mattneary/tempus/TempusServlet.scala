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
import net.liftweb.json.JsonDSL._
import java.io.{File,FileInputStream,FileOutputStream}

class TempusServlet extends TempusStack {
  val dest = "/Users/mattneary/Desktop/TempusServer/"
  val file = "index.html"
  def renderPage(file: String) {
    org.scalatra.util.io.copy(new FileInputStream(file), response.getOutputStream)
  }
  def parseNum(s: String) : Int = s.toInt
  def structure(offset: Int) : Array[Block] = {
    val offsets = HashMap( 1 -> 3, 2 -> 5, 3 -> 7, 4 -> 10, 5 -> 12, 6 -> 14, 7 -> 17, 8 -> 19 )
    val hr = new Block(7, false)
    val n = new Block(32, true)
    val l = new Block(74, false)
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
    val minStart = comps(0)*60 + comps(1) - 470
    val minEnd = 913 - 470
    val period = parseNum(request.getParameter("period"))
    val calced = calc(structure(period), minEnd - minStart).mkString(" | ")
    response.getOutputStream.print(minStart+" start\n"+calced+"\n")
    response.getOutputStream.close()
  }
}
