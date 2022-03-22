package funsets

/** This class is a test suite for the methods in object FunSets.
  *
  * To run this test suite, start "sbt" then run the "test" command.
  */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /** When writing tests, one would often like to re-use certain values for
    * multiple tests. For instance, we would like to create an Int-set and have
    * multiple test about it.
    *
    * Instead of copy-pasting the code for creating the set into every test, we
    * can store it in the test class using a val:
    *
    * val s1 = singletonSet(1)
    *
    * However, what happens if the method "singletonSet" has a bug and crashes?
    * Then the test methods are not even executed, because creating an instance
    * of the test class fails!
    *
    * Therefore, we put the shared values into a separate trait (traits are like
    * abstract classes), and create an instance inside each test method.
    */

  trait TestSets:
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

  /** This test is currently disabled (by using @Ignore) because the method
    * "singletonSet" is not yet implemented and the test would fail.
    *
    * Once you finish your implementation of "singletonSet", remove the .ignore
    * annotation.
    */
  test("singleton set one contains one") {

    /** We create a new instance of the "TestSets" trait, this gives us access
      * to the values "s1" to "s3".
      */
    new TestSets:
      /** The string argument of "assert" is a message that is printed in case
        * the test fails. This helps identifying which assertion failed.
        */
      assert(contains(s1, 1), "Singleton")
  }

  test("union contains all elements of each set") {
    new TestSets:
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
  }

  test("test intersect") {
    new TestSets:
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "Not Intersect 1")
      assert(!contains(s, 2), "Not Intersect 2")
      assert(!contains(s, 3), "Not Intersect 3")

      val sUnion = union(s1, s2)
      val sIntersect = intersect(sUnion, s1)
      assert(contains(sIntersect, 1), "Intersect 1")
  }

  test("test diff") {
    new TestSets:
      val sUnion = union(s1, s2) // {1} U {2} = {1, 2}
      val sIntersect = intersect(sUnion, s1) // {1, 2} INTER {1} = {1}
      val s = diff(sUnion, sIntersect) // {1, 2} diff {1} = {2}
      assert(!contains(s, 1), "Diff 1")
      assert(contains(s, 2), "Diff 2")
  }

  test("test filter") {
    new TestSets:
      val sUnion = union(union(s1, s2), s3) // {1, 2, 3}
      assert(contains(sUnion, 3), "Filter 0")
      val s = filter(sUnion, _ < 3)
      assert(contains(s, 1), "Filter 1")
      assert(contains(s, 2), "Filter 2")
      assert(!contains(s, 3), "Filter 3")
  }

  test("test forall") {
    new TestSets:
      val s = union(union(s1, s2), s3)
      assert(!forall(s, _ % 2 == 0), "forall 1")
      assert(forall(s, _ < 120), "forall 2")
  }

  test("test exists") {
    new TestSets:
      val s = union(union(s1, s2), s3)
      assert(exists(s, _ == 3), "exists 1")
      assert(!exists(s, _ > 3), "exists 2")
  }

  test("test map") {
    new TestSets:
      val s = map(union(union(s1, s2), s3), 2 * _)
      assert(contains(s, 6), "map 1")
      assert(!contains(s, 3), "map 2")
  }

  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
