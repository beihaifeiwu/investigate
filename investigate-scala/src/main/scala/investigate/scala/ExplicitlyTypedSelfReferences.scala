package investigate.scala

/**
 * Created by LiuPin on 2015/8/17.
 */
object ExplicitlyTypedSelfReferences extends App{

  abstract class Graph {
    type Edge
    type Node <: NodeIntf
    abstract class NodeIntf {
      def connectWith(node: Node): Edge
    }
    def nodes: List[Node]
    def edges: List[Edge]
    def addNode: Node
  }


  abstract class DirectedGraph extends Graph {
    type Edge <: EdgeImpl
    class EdgeImpl(origin: Node, dest: Node) {
      def from = origin
      def to = dest

      override def toString: String = from + " -> " + to
    }
    class NodeImpl extends NodeIntf {
      self: Node =>
      def connectWith(node: Node): Edge = {
        val edge = newEdge(this, node)
        edges = edge :: edges
        edge
      }
    }

    protected def newNode: Node
    protected def newEdge(from: Node, to: Node): Edge

    var nodes: List[Node] = Nil
    var edges: List[Edge] = Nil

    def addNode: Node = {
      val node = newNode
      nodes = node :: nodes
      node
    }
  }

  class ConcreteDirectedGraph extends DirectedGraph {
    type Edge = EdgeImpl
    type Node = NodeImpl
    protected def newNode: Node = new NodeImpl
    protected def newEdge(f: Node, t: Node): Edge = new EdgeImpl(f, t)

    override def toString: String = edges.mkString("\n")
  }

  val g: Graph = new ConcreteDirectedGraph
  val n1 = g.addNode
  val n2 = g.addNode
  val n3 = g.addNode
  n1.connectWith(n2)
  n2.connectWith(n3)
  n1.connectWith(n3)

  println(g)
}
