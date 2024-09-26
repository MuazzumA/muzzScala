class Bill (private var total: Double) {

  def getTotal: Double = total
  def addToTotal(value: Double): Unit = {total += value}
}
 