package hu.bme.aut.httpmoneyapidemo.data

data class MoneyResult(val success: Boolean?, val timestamp: Number?,
                       val base: String?, val date: String?, val rates: Rates?)

data class Rates(val AED: Number?, val AFN: Number?, val ALL: Number?, val AMD: Number?, val ANG: Number?, val AOA: Number?, val ARS: Number?, val AUD: Number?, val AWG: Number?, val AZN: Number?, val BAM: Number?, val BBD: Number?, val BDT: Number?, val BGN: Number?, val BHD: Number?, val BIF: Number?, val BMD: Number?, val BND: Number?, val BOB: Number?, val BRL: Number?, val BSD: Number?, val BTC: Number?, val BTN: Number?, val BWP: Number?, val BYN: Number?, val BYR: Number?, val BZD: Number?, val CAD: Number?, val CDF: Number?, val CHF: Number?, val CLF: Number?, val CLP: Number?, val CNY: Number?, val COP: Number?, val CRC: Number?, val CUC: Number?, val CUP: Number?, val CVE: Number?, val CZK: Number?, val DJF: Number?, val DKK: Number?, val DOP: Number?, val DZD: Number?, val EGP: Number?, val ERN: Number?, val ETB: Number?, val EUR: Number?, val FJD: Number?, val FKP: Number?, val GBP: Number?, val GEL: Number?, val GGP: Number?, val GHS: Number?, val GIP: Number?, val GMD: Number?, val GNF: Number?, val GTQ: Number?, val GYD: Number?, val HKD: Number?, val HNL: Number?, val HRK: Number?, val HTG: Number?, val HUF: Number?, val IDR: Number?, val ILS: Number?, val IMP: Number?, val INR: Number?, val IQD: Number?, val IRR: Number?, val ISK: Number?, val JEP: Number?, val JMD: Number?, val JOD: Number?, val JPY: Number?, val KES: Number?, val KGS: Number?, val KHR: Number?, val KMF: Number?, val KPW: Number?, val KRW: Number?, val KWD: Number?, val KYD: Number?, val KZT: Number?, val LAK: Number?, val LBP: Number?, val LKR: Number?, val LRD: Number?, val LSL: Number?, val LTL: Number?, val LVL: Number?, val LYD: Number?, val MAD: Number?, val MDL: Number?, val MGA: Number?, val MKD: Number?, val MMK: Number?, val MNT: Number?, val MOP: Number?, val MRO: Number?, val MUR: Number?, val MVR: Number?, val MWK: Number?, val MXN: Number?, val MYR: Number?, val MZN: Number?, val NAD: Number?, val NGN: Number?, val NIO: Number?, val NOK: Number?, val NPR: Number?, val NZD: Number?, val OMR: Number?, val PAB: Number?, val PEN: Number?, val PGK: Number?, val PHP: Number?, val PKR: Number?, val PLN: Number?, val PYG: Number?, val QAR: Number?, val RON: Number?, val RSD: Number?, val RUB: Number?, val RWF: Number?, val SAR: Number?, val SBD: Number?, val SCR: Number?, val SDG: Number?, val SEK: Number?, val SGD: Number?, val SHP: Number?, val SLL: Number?, val SOS: Number?, val SRD: Number?, val STD: Number?, val SVC: Number?, val SYP: Number?, val SZL: Number?, val THB: Number?, val TJS: Number?, val TMT: Number?, val TND: Number?, val TOP: Number?, val TRY: Number?, val TTD: Number?, val TWD: Number?, val TZS: Number?, val UAH: Number?, val UGX: Number?, val USD: Number?, val UYU: Number?, val UZS: Number?, val VEF: Number?, val VND: Number?, val VUV: Number?, val WST: Number?, val XAF: Number?, val XAG: Number?, val XAU: Number?, val XCD: Number?, val XDR: Number?, val XOF: Number?, val XPF: Number?, val YER: Number?, val ZAR: Number?, val ZMK: Number?, val ZMW: Number?, val ZWL: Number?)