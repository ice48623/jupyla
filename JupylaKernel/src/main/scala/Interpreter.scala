import scala.tools.nsc.{Interpreter, Settings}

object Interpreter {

	// case class Session(session_Id : Int){
	// 	val output: Map[Int, String] = Map()
	// 	def storeOutput(output: String, session_Id: Int){
	// 		output + (session_Id -> output)
	// 		println("Enter")
	// 		println(output)
	// 	}
	// }

	// val sr = new java.io.StringWriter()
	// val sr = new java.io.OutputStream
	// val out = new java.io.PrintWriter(sr)

	// var i = new Interpreter(new Settings(), out)
	var i = new Interpreter(new Settings())

	// 

	// println(i.getClass.getSimpleName)
	// val nums: Map[Session, Interpreter] = Map()

  	def interpret(code: String) = {
  		// val l: String = ""
  		var lines = code.split("\n")
  		
	    var l = lines.map(line => i.interpret(line))
	    
	    
	}
	// 
  	def main(args: Array[String]): Unit = {
	  	// println(args(0))
	  	
	    // asuume that each cell represent a cell in jupyter
		// val q: List[Array[String]] = List(Array("var x = 1\n"), Array("x+=1\n"),  Array("println(x)"), Array("var x = 3\n"))
		val q: List[Array[String]] = List(Array("def fib(n : Int): Long = if (n<2) 1 else fib(n-1) + fib(n-2)"), Array("fib(3)"))


	    handler(q)
	    // val s = sr.toString
	    // println(s)
	}
	// assume : queue already contain codes from somewhere.
	def handler(queue: List[Array[String]]): Unit = {
		queue.map(cell => cell.foldLeft(""){(ans,i) => ans + i})
			 .map(result => interpret(result))



  	}

}
