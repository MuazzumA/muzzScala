class Person (private val firstName: String, private val lastName: String){
  private var room: Option[Room] = None

  def fullName: String = s"$firstName $lastName"

  def assignRoom(room: Option[Room]): Unit = {
    this.room = room
  }
  
  def getRoom: Option[Room] = room

}
