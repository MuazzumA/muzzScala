class Room (private val roomType: String, private var cost: Int, val id: Int){

  def getRoomType: String = s"$roomType"
  def getCost: Int = cost

  override def toString: String = s"Room ID: $id, Type: $roomType, Cost: $cost"

}

object Room{
  private var counter: Int = 0

  // Method to create a new Room with an auto-incrementing ID
  def create(roomType: String, cost: Int): Room = {
    counter += 1 // Increment the counter for each new room
    new Room(roomType, cost, counter) // Pass the counter as the ID
  }
}
