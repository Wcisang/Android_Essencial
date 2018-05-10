package cisang.com.android_essencial.extensions

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.ByteArrayInputStream
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Created by WCisang on 09/05/2018.
 */
class Xml(val root: Element) {

    fun getChildren(name: String): List<Node> {
        return getChildren(root, name)
    }

    fun getChildren(node: Node = root, name: String): List<Node> {
        val children = ArrayList<Node>()
        val nodes = node.childNodes
        if (nodes != null && nodes.length >= 1) {
            for (i in 0..nodes.length - 1) {
                val n = nodes.item(i)
                if (name == n.nodeName) {
                    children.add(n)
                }
            }
        }
        return children
    }

}

    fun Node.getChild(tag: String): Node? {
        val childNodes = childNodes ?: return null
        for (i in 0..childNodes.length - 1) {
            val item = childNodes.item(i)
            if (item != null){
                val name = item.nodeName
                if (tag.equals(name, ignoreCase = true)){
                    return item
                }
            }
        }
        return null
    }

    fun Node.getText(tag: String): String {
        val n = getChild(tag)
        if (n != null) {
            val text = n.firstChild
            if (text != null) {
                val s = text.nodeValue
                return s.trim {it <= ' '}
            }
        }
        return ""
    }

    fun String.getXml(charset: String = "UTF-8"): Xml {
        try {
            val xml = this
            val factory = DocumentBuilderFactory.newInstance()
            factory.isValidating = false
            val bytes = xml.toByteArray(charset(charset))
            val inputStream = ByteArrayInputStream(bytes)
            val builder = factory.newDocumentBuilder()
            val dom = builder.parse(inputStream)
            val root = dom.documentElement ?: throw RuntimeException("XML incorreto")
            return Xml(root)
        }catch (e : Exception){
            throw RuntimeException(e.message)
        }
    }
