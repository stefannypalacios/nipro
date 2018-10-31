package sv.com.nipro.interfaz.utils;

public class Constans {
//	public static String FILE_PATH = "C:\\BM-105247-S-20180920.txt";
	public static String FILE_PATH = "C:\\BM-105247_2018-10-22.xml";
	
	public static String PDW_SALT = "~q)gb%H+<vmLTe]Xx7m%u&DO--&P6I<YvE|utbd6V60lGRO#fq;Z)$Jr1S-zqyH{";
	
	public static String PORTAL_USER = "WEB";
	public static String REST_USER = "REST";
	
	public static String USER_TYPE_WEB = "WEB";
	public static String TRANSACTION_TYPE_OUTPUT = "OUTPUT";
	public static String TRANSACTION_TYPE_INPUT = "INPUT";
	
	public static String METOHD_TYPE_CHECKOUT = "Checkout";
	public static String METOHD_TYPE_CHECKIN = "Checkin";
	public static String METOHD_TYPE_ACCEPTMESSAGE = "AcceptMessage";
	
	public static String FORMAT_DATE_HL7 = "yyyyymmddhhmm";
	
	public static String MSH = "MSH|^~\\&|NIPROINTERFAZ|{CONFIRMA_ID}^{SUMINISTRANTE}|SIAP|MINSAL|{FECHA_GEN}||OUL^R22|2|D|2.5.1|||AL|AL\n";
	public static String ORC = "ORC|{COD_CTLR}|{ID_SOLICITUD}|||CM||||{FECHA_ENVIO}|||{COD_EMPLEADO}^{EMPLEADO}\n";
	public static String OBR = "OBR|1|{ID_EXAMEN_SOL}||195^HEMOGRAMA AUTOMATIZADO^L^HC^HEMOGRAMA COMPLETO^L||||{FECHA_GENERADO}||2||||||{COD_EMPLEADO}^{EMPLEADO}||||||{FECHA_RP_FINAL}||HM|F\n";
	public static String OBX_1 = "OBX|1|ST|{ID_EXAMEN}|{ID_RESULTADO}|{RESULTADO}\n";
	public static String OBX = "OBX|{AUTOINCREMENTO}|NM|{ID_AGENTE}^{AGENTE}|Instrumento|{VALOR_R}|{UNIDAD}|{R_SUP} - {R_INF}||||F|||{FECHA_RESULT}\n";
}
