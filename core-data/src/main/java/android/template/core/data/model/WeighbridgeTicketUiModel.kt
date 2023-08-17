package android.template.core.data.model

data class WeighbridgeTicketUiModel(
    val id: String = "",
    val weighingDate: String = "",
    val driverName: String = "",
    val licenseNumber: String = "",
    val inWeight: Double = 0.0,
    val outWeight: Double = 0.0
) {
    fun getNetWeight(): String {
        return if (outWeight != 0.0) {
            "${outWeight - inWeight} Tons"
        } else {
            "-"
        }
    }

    fun getInWeightOrEmpty(): String {
        return if (inWeight != 0.0) {
            inWeight.toString()
        } else {
            ""
        }
    }

    fun getOutWeightOrEmpty(): String {
        return if (outWeight != 0.0) {
            outWeight.toString()
        } else {
            ""
        }
    }
}
