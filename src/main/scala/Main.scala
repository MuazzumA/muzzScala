import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.*

object Main {

  def main(args: Array[String]): Unit = {

    // it's better to use ListBuffer because it makes it easy to add elements later (mutable)
    // Scala lists are immutable, using them instead would make new lists every append
    val availableRooms = ListBuffer[Room]()
    val bookedRooms = ListBuffer[Room]()

    breakable {
      while (true) {
        // Hotel Menu
        println("--------------------------")
        println("HOTEL MANAGEMENT SYSTEM")
        println("--------------------------")
        println(
          """1 - Book Room
            |2 - Issue Bill
            |3 - Manage Rooms
            |0 - Exit""".stripMargin)

        val input = scala.io.StdIn.readInt()

        input match
          case 1 =>
            val guest = createGuest()
            if (bookRoom(guest, availableRooms, bookedRooms)){
              println("Successfully booked " + guest.fullName + " to " + guest.getRoom.get.getRoomType)
            } else {
              println("Cannot book")
             }
          case 2 => issueBill()
          case 3 => manageRooms(availableRooms, bookedRooms)
          case 0 => break()
      }
    }
  }

  private def createGuest(): Person = {
    println("Enter first name: ")
    val firstName = scala.io.StdIn.readLine()
    println("Enter last name: ")
    val lastName = scala.io.StdIn.readLine()
    return new Person(firstName, lastName)
  }

  private def bookRoom(person: Person, availableRooms: ListBuffer[Room], bookedRooms: ListBuffer[Room]): Boolean = {
    if (availableRooms.isEmpty) {
      println("No rooms available.")
      return false
    }
    // show all rooms
    for ((room, i) <- availableRooms.zipWithIndex) {
      println(i + " " +  room)
    }
    println("Pick room ...")
    val input = scala.io.StdIn.readInt()

    val selectedRoomOption: Option[Room] = availableRooms.lift(input)

    selectedRoomOption match {
      case Some(room) =>
        person.assignRoom(Some(room)) // Passing Some(room) to assignRoom
        availableRooms.remove(input)
        bookedRooms.addOne(room)
        true
      case None =>
        println("Invalid room selection.")
        false
    }
  }

  private def issueBill(): Double = {
    return 0.0
  }

  // Unit is scala's equivalent to java's void return type
  private def manageRooms(availableRooms: ListBuffer[Room], bookedRooms: ListBuffer[Room]): Unit = {
    println("------------------")
    println("ROOM MANAGEMENT")
    println("------------------")
    println(
      """1 - View all available Rooms
        |2- View all booked Rooms
        |3 - Add a room""".stripMargin)

    val input = scala.io.StdIn.readInt()
    input match
      case 1 =>
        for (room <- availableRooms) {
          println(room)
        }
      case 2 => for (room <- bookedRooms) {
        println(room)
      }
      case 3 => addRoom(availableRooms)
  }

  private def addRoom(list: ListBuffer[Room]): Unit = {
    println(
      """Select Room Type
        |1 - Family
        |2 - Single""".stripMargin)

    val input = scala.io.StdIn.readInt()
    input match
      case 1 =>
        val familyRoom = Room.create("Family", 300)
        list.addOne(familyRoom)
      case 2 =>
        val singleRoom = Room.create("Single", 200)
        list.addOne(singleRoom)
  }

}
