package dev.robaldo.viaggiatreno.models.trains

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StationBoardTrain(
    @SerialName("arrivato")
    val arrivato: Boolean? = null,

    @SerialName("dataPartenzaTrenoAsDate")
    val dataPartenzaTrenoAsDate: String? = null,

    @SerialName("dataPartenzaTreno")
    val dataPartenzaTreno: Long? = null,

    @SerialName("partenzaTreno")
    val partenzaTreno: Long? = null,

    @SerialName("millisDataPartenza")
    val millisDataPartenza: String? = null,

    @SerialName("numeroTreno")
    val numeroTreno: String? = null,

    @SerialName("categoria")
    val categoria: String? = null,

    @SerialName("categoriaDescrizione")
    val categoriaDescrizione: String? = null,

    @SerialName("origine")
    val origine: String? = null,

    @SerialName("codOrigine")
    val codOrigine: String? = null,

    @SerialName("destinazione")
    val destinazione: String? = null,

    @SerialName("codDestinazione")
    val codDestinazione: String? = null,

    @SerialName("origineEstera")
    val origineEstera: String? = null,

    @SerialName("destinazioneEstera")
    val destinazioneEstera: String? = null,

    @SerialName("oraPartenzaEstera")
    val oraPartenzaEstera: String? = null,

    @SerialName("oraArrivoEstera")
    val oraArrivoEstera: String? = null,

    @SerialName("tratta")
    val tratta: Int? = null,

    @SerialName("regione")
    val regione: Int? = null,

    @SerialName("origineZero")
    val origineZero: String? = null,

    @SerialName("destinazioneZero")
    val destinazioneZero: String? = null,

    @SerialName("orarioPartenzaZero")
    val orarioPartenzaZero: String? = null,

    @SerialName("orarioArrivoZero")
    val orarioArrivoZero: String? = null,

    @SerialName("circolante")
    val circolante: Boolean? = null,

    @SerialName("codiceCliente")
    val codiceCliente: Int? = null,

    @SerialName("binarioEffettivoArrivoCodice")
    val binarioEffettivoArrivoCodice: String? = null,

    @SerialName("binarioEffettivoArrivoDescrizione")
    val binarioEffettivoArrivoDescrizione: String? = null,

    @SerialName("binarioEffettivoArrivoTipo")
    val binarioEffettivoArrivoTipo: String? = null,

    @SerialName("binarioProgrammatoArrivoCodice")
    val binarioProgrammatoArrivoCodice: String? = null,

    @SerialName("binarioProgrammatoArrivoDescrizione")
    val binarioProgrammatoArrivoDescrizione: String? = null,

    @SerialName("binarioEffettivoPartenzaCodice")
    val binarioEffettivoPartenzaCodice: String? = null,

    @SerialName("binarioEffettivoPartenzaDescrizione")
    val binarioEffettivoPartenzaDescrizione: String? = null,

    @SerialName("binarioEffettivoPartenzaTipo")
    val binarioEffettivoPartenzaTipo: String? = null,

    @SerialName("binarioProgrammatoPartenzaCodice")
    val binarioProgrammatoPartenzaCodice: String? = null,

    @SerialName("binarioProgrammatoPartenzaDescrizione")
    val binarioProgrammatoPartenzaDescrizione: String? = null,

    @SerialName("subTitle")
    val subTitle: String? = null,

    @SerialName("esisteCorsaZero")
    val esisteCorsaZero: Boolean? = null,

    @SerialName("orientamento")
    val orientamento: String? = null,

    @SerialName("inStazione")
    val inStazione: Boolean? = null,

    @SerialName("haCambiNumero")
    val haCambiNumero: Boolean? = null,

    @SerialName("nonPartito")
    val nonPartito: Boolean? = null,

    @SerialName("provvedimento")
    val provvedimento: Int? = null,

    @SerialName("riprogrammazione")
    val riprogrammazione: String? = null,

    @SerialName("orarioPartenza")
    val orarioPartenza: Long? = null,

    @SerialName("orarioArrivo")
    val orarioArrivo: Long? = null,

    @SerialName("stazionePartenza")
    val stazionePartenza: String? = null,

    @SerialName("stazioneArrivo")
    val stazioneArrivo: String? = null,

    @SerialName("statoTreno")
    val statoTreno: String? = null,

    @SerialName("corrispondenze")
    val corrispondenze: List<String>? = null,

    @SerialName("servizi")
    val servizi: List<String>? = null,

    @SerialName("ritardo")
    val ritardo: Int? = null,

    @SerialName("tipoProdotto")
    val tipoProdotto: String? = null,

    @SerialName("compOrarioPartenzaZeroEffettivo")
    val compOrarioPartenzaZeroEffettivo: String? = null,

    @SerialName("compOrarioArrivoZeroEffettivo")
    val compOrarioArrivoZeroEffettivo: String? = null,

    @SerialName("compOrarioPartenzaZero")
    val compOrarioPartenzaZero: String? = null,

    @SerialName("compOrarioArrivoZero")
    val compOrarioArrivoZero: String? = null,

    @SerialName("compOrarioArrivo")
    val compOrarioArrivo: String? = null,

    @SerialName("compOrarioPartenza")
    val compOrarioPartenza: String? = null,

    @SerialName("compNumeroTreno")
    val compNumeroTreno: String? = null,

    @SerialName("compOrientamento")
    val compOrientamento: List<String>? = null,

    @SerialName("compTipologiaTreno")
    val compTipologiaTreno: String? = null,

    @SerialName("compClassRitardoTxt")
    val compClassRitardoTxt: String? = null,

    @SerialName("compClassRitardoLine")
    val compClassRitardoLine: String? = null,

    @SerialName("compImgRitardo2")
    val compImgRitardo2: String? = null,

    @SerialName("compImgRitardo")
    val compImgRitardo: String? = null,

    @SerialName("compRitardo")
    val compRitardo: List<String>? = null,

    @SerialName("compRitardoAndamento")
    val compRitardoAndamento: List<String>? = null,

    @SerialName("compInStazionePartenza")
    val compInStazionePartenza: List<String>? = null,

    @SerialName("compInStazioneArrivo")
    val compInStazioneArrivo: List<String>? = null,

    @SerialName("compOrarioEffettivoArrivo")
    val compOrarioEffettivoArrivo: String? = null,

    @SerialName("compDurata")
    val compDurata: String? = null,

    @SerialName("compImgCambiNumerazione")
    val compImgCambiNumerazione: String? = null,

    @SerialName("materiale_label")
    val materialeLabel: String? = null,

    @SerialName("ultimoRilev")
    val ultimoRilev: Long? = null,

    @SerialName("iconTreno")
    val iconTreno: String? = null
)