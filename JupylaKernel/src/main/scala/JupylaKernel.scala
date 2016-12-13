import org.zeromq.ZMQ
import org.zeromq.ZeroMQ
import org.zeromq.ZMQ.{Context, Socket}
import play.api.libs.json._
import play.api.libs.json.JsValue


import scala.io.Source

object JupylaKernel {

  def main(args: Array[String]) = {
    val filename = args(0)
    // println("Input argument", filename)
    val fileContents = Source.fromFile(filename).getLines.mkString
    // println("File Content", fileContents)

    val json: JsValue = Json.parse(fileContents)
    // val readableString: String = Json.prettyPrint(json)
    // println(readableString)
    val configAttributes = Seq(
      "control_port", "stdin_port", "ip", "hb_port", "signature_scheme",
      "key", "kernel_name", "shell_port", "transport", "iopub_port"
    )
    val config: Map[String, String] = configAttributes
      .map {name => (name, (json \ name).toString)}
      .toMap

    val trans = config("transport")
    val ip = config("ip")

    def make_sck(ctx: Context)(port: Int, qtype: Int)(on_recv: (Socket, Array[Byte]) => Unit): Unit = {
      val sck = ctx.socket(qtype)
      val connectTo = s"${trans}://${ip}:${port}"
      sck.bind(connectTo)
      val listenerThread = new Thread {
        override def run: Unit = {
          while(true){
            val rcvdArray = sck.recv(0)
            on_recv(sck, rcvdArray)
          }
        }
      }

      listenerThread.start()
    }

    val hb_port = config("hb_port").toInt
    val ctx: Context = ZMQ.context(1)
    //-- Heartbeat --//
    def makeListener = make_sck(ctx)
    makeListener(ZMQ.REP, hb_port) { (skt, rcvd) =>
      heartbeat_loop(skt, rcvd)
    }

    def heartbeat_loop(heartbeat_socket: Socket, rcvd: Array[Byte]) = {
      ZMQ.zmq.zmq_device(ZMQ.FORWARDER, heartbeat_socket, heartbeat_socket)
    }

    makeListener





    def iopub_handler(revd: Array[Byte]) = {
      println("io received: ", revd.toString)
    }

    def stdin_handler(revd: Array[Byte]) = {
      println("stdin received: ", revd.toString)
    }

    def control_handler(revd: Array[Byte], sck: Socket) = ???

//    def deserialize_wire_msg(revd: Array[Byte]) = {
//      val delim_idx = revd.indexOf('DELIM')
//      val identities = revd.splitAt(delim_idx)._1
//      val m_signature = revd.indexOf(delim_idx+1)
//      val msg_frames = revd.splitAt(delim_idx+2)._2
//
//      def decode(msg: Byte) = {
//        val s = new String(msg, "ASCII")
//      }
//
//    }

    val heartbeatThread = new Thread {
      override def run: Unit = {

        val heartbeat_socket: Socket = ctx.socket(ZMQ.REP)
        val trans = (json \ "transport").toString()
        val hb = (json \ "hb_port").toString()
        val hb_ip = (json \ "ip").toString()
        val conn = trans + "://" + hb_ip + ":" + hb

        while(true){
          val receive = heartbeat_socket.recv(0)
          heartbeat_socket.send(receive, 0)
        }

      }
    }

    heartbeatThread.start()


    val iopubThread = new Thread {
      override def run: Unit = {
        val iopub_socket = ctx.socket(ZMQ.PUB)
        val trans = (json \ "transport").toString()
        val hb = (json \ "hb_port").toString()
        val hb_ip = (json \ "ip").toString()
        val conn = trans + "://" + hb_ip + ":" + hb
        while(true){
          val receive = iopub_socket.recv(0)
//          if (receive.length != 0) {
//            println(receive)
//            iopub_socket.send(receive, 0)
//          }
          println(receive)

        }

      }
    }

    iopubThread.start()

    val controlThread = new Thread {
      override def run: Unit = {
        val control_socket = ctx.socket(ZMQ.ROUTER)
        val trans = (json \ "transport").toString()
        val hb = (json \ "hb_port").toString()
        val hb_ip = (json \ "ip").toString()
        val conn = trans + "://" + hb_ip + ":" + hb
        while(true){
          val receive = control_socket.recv(0)
//          if (receive.length != 0) {
//            println(receive)
//            control_socket.send(receive, 0)
//          }
          println(receive)

        }

      }
    }

    controlThread.start()

    val std_inThread = new Thread {
      override def run: Unit = {
        val std_in_socket = ctx.socket(ZMQ.ROUTER)
        val trans = (json \ "transport").toString()
        val io = (json \ "iopub_port").toString()
        val std_ip = (json \ "ip").toString()
        val conn = trans + "://" + std_ip + ":" + io
        while(true){
          val receive = std_in_socket.recv(0)
//          if (receive.length != 0) {
//            println(receive)
//            std_in_socket.send(receive, 0)
//          }
          println(receive)

        }

      }
    }

    std_inThread.start()

    val shellThread = new Thread {
      override def run: Unit = {
        val shell_socket = ctx.socket(ZMQ.ROUTER)
        val trans = (json \ "transport").toString()
        val hb = (json \ "hb_port").toString()
        val hb_ip = (json \ "ip").toString()
        val conn = trans + "://" + hb_ip + ":" + hb
        while(true){
          val receive = shell_socket.recv(0)
//          if (receive.length != 0) {
//            println(receive)
//            shell_socket.send(receive, 0)
//          }
          println(receive)

        }

      }
    }

    shellThread.start()

  }

}
