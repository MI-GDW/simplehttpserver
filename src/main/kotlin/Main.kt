import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

fun main(args: Array<String>) {
    val server = HttpServer.create(InetSocketAddress(8000), 0)
    server.createContext("/", MyHandler())
    server.executor = null // creates a default executor
    server.start()
    println("Server listing on port 8000")
}

class MyHandler : HttpHandler {
    override fun handle(httpExchange: HttpExchange) {
        val payload = "This is the payload"
        httpExchange.sendResponseHeaders(200, payload.length.toLong())
        val os = httpExchange.responseBody
        os.write(payload.toByteArray())
        os.close()
    }
}