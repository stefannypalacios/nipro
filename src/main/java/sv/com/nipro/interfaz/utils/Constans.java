package sv.com.nipro.interfaz.utils;

public class Constans {

	public static String PDW_SALT = "~q)gb%H+<vmLTe]Xx7m%u&DO--&P6I<YvE|utbd6V60lGRO#fq;Z)$Jr1S-zqyH{";

	public static final String USER_TYPE_REST = "REST";

	public static String USER_TYPE_WEB = "WEB";
	public static String TRANSACTION_TYPE_OUTPUT = "OUTPUT";
	public static String TRANSACTION_TYPE_INPUT = "INPUT";

	public static String METOHD_TYPE_CHECKOUT = "Checkout";
	public static String METOHD_TYPE_CHECKIN = "Checkin";
	public static String METOHD_TYPE_ACCEPTMESSAGE = "AcceptMessage";

	public static String FORMAT_DATE_HL7 = "yyyymmddhhmm";

	public static String MSH = "MSH|^~\\&|NIPROINTERFAZ|{SUMINISTRANTE_ID}^{SUMINISTRANTE}|SIAP|MINSAL|{FECHA_GEN}||OUL^R22|2|D|2.5.1|||AL|AL\n";
	public static String ORC = "ORC|{COD_CTLR}|{ID_SOLICITUD}|||CM||||{FECHA_ENVIO}|||{COD_EMPLEADO}^{EMPLEADO}\n";
	public static String OBR = "OBR|1|{ID_EXAMEN_SOL}||195^HEMOGRAMA AUTOMATIZADO^L^HC^HEMOGRAMA COMPLETO^L||||{FECHA_GENERADO}||2||||||{COD_EMPLEADO}^{EMPLEADO}||||||{FECHA_RP_FINAL}||HM|F\n";

	// ID del resultado cualitativo SEPS
	public static String OBX_1 = "OBX|1|ST|195|{ID_RESULTADO}|{RESULTADO}\n";
	public static String OBX = "OBX|{AUTOINCREMENTO}|NM|{ID_AGENTE}^{AGENTE}|Instrumento|{VALOR_R}|{UNIDAD}|{R_SUP} - {R_INF}||||F|||{FECHA_RESULT}\n";

	/**
	 * TODO:: Se deben de moficar
	 */
	public static final String URL_WS = "http://siaps.localhost/app_dev.php/api/";
	public static final String NAME_SERVICE_CHECKIN = "checkin";
	public static final String NAME_SERVICE_ACCEPTMESSAGE = "acceptMessage";
	public static final String NAME_SERVICE_CHECKOUT = "checkout";
	public static final String FILE_PATH = "C:\\Users\\osegueda\\Documents\\nipro-docs\\BouleDataInterface\\BM800_Data\\BM-105247_2018-10-22.xml";

	public String mensajeReqCheckin(String user, String pass){
		String mensaje = "";
		
		return mensaje;
	}
	public static String ucFirst(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		} else {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
	}
}
