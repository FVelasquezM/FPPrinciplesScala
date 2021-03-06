package patmat

class HuffmanSuite extends munit.FunSuite:
  import Huffman.*

  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }


  test("weight of a larger tree (10pts)") {
    new TestTrees:
      assertEquals(weight(t1), 5)
  }


  test("chars of a larger tree (10pts)") {
    new TestTrees:
      assertEquals(chars(t2), List('a','b','d'))
  }

  test("string2chars hello world") {
    assertEquals(string2Chars("hello, world"), List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("make ordered leaf list for some frequency table (15pts)") {
    assertEquals(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))), List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("combine of some leaf list (15pts)") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assertEquals(combine(leaflist), List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }


  test("decode and encode a very short text should be identity (10pts)") {
    new TestTrees:
      assertEquals(decode(t1, encode(t1)("ab".toList)), "ab".toList)
  }

  test("times"){
    assertEquals(times(List('a', 'b', 'a')), List(('a', 2), ('b', 1)))
    assertEquals(times(List('a', 'b', 'a', 'a', 'c')), List(('a', 3), ('b', 1), ('c',1)))
  }

  test("makeOrderedLeafList"){
    assertEquals(makeOrderedLeafList(List(('a', 2), ('b', 1))), List(Leaf('b', 1), Leaf('a', 2)))
    assertEquals(makeOrderedLeafList(List(('a', 3), ('b', 1), ('c',1))), List(Leaf('c', 1), Leaf('b', 1), Leaf('a', 3))) 
  }

  test("singleton"){
    assertEquals(singleton(List(Leaf('a', 1))), true)
    assertEquals(singleton(List(Leaf('a', 1), Leaf('b', 1))), false)
  }

  test("decoded secret"){
    println(decodedSecret.mkString)
  }

  test("convert"){
    new TestTrees:
      val table = convert(t1)
      println(table)
      println(codeBits(table)('b'))
  }

  test("quick encode"){
    new TestTrees:
      assertEquals(decode(t1, quickEncode(t1)("ab".toList)), "ab".toList)
  }
  


  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
