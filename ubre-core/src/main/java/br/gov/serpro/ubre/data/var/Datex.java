package br.gov.serpro.ubre.data.var;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.exception.FreezeException;

/**
 * [DATE EXTENSION] Data imutável estendida.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 21/10/2015
 * 
 */
public class Datex extends Date {

	private static final String DATEX_CANNOT_CHANGE = "O código fonte está tentando fazer uma alteração indevida em um objeto imutável da classe Datex";
	private static final String DATEX_INVALID_TOKEN = "Data ou hora inválida:";
	public static final long MILIS_IN_ONE_DAY = 86400000; // 1000 * 60 * 60 * 24
	private static final Logger LOGGER = Logger.getLogger("Datex");

	private static final long serialVersionUID = 1L;
	public static final Datex ZERO = new Datex("01/01/0001 00:00:00");

	private boolean useDate;
	private boolean useTime;

	public Datex(String source) {
		super();
		if (source.contains("/")) {
			useDate = true;
			if (source.contains(":")) {
				useTime = true;
			} else {
				useTime = false;
			}
		} else {
			useTime = true;
		}
		DateFormat dateFormat = getDateFormat();
		Date date;
		try {
			date = dateFormat.parse(source);
		} catch (ParseException e) {
			throw new CTDException(DATEX_INVALID_TOKEN, e);
		}
		super.setTime(date.getTime());
	}

	/**
	 * Cria uma instância com milisegundos igual a uma instância de Date.
	 * Equivale a fazer <code>new Date()</code>. Este construtor não faz nenhuma
	 * formatação e é muito mais rápido (100 vezes) que o que recebe dois
	 * booleanos.
	 */
	public Datex() {
		useDate = true;
		useTime = true;
		super.setTime(new Date().getTime());
	}

	public Datex(boolean useDate, boolean useTime) {
		this.useDate = useDate;
		this.useTime = useTime;
		super.setTime(new Date().getTime());
		DateFormat dateFormat = getDateFormat();
		String source = dateFormat.format(this);
		try {
			super.setTime(dateFormat.parse(source).getTime());
		} catch (ParseException e) {
			throw new CTDException(DATEX_INVALID_TOKEN, e);
		}
	}

	/**
	 * Construtor para datas sem time a partir dos campos ano, mês e dia
	 * separados.
	 * 
	 * @param year
	 *            O inteiro que corresponde ao ano.
	 * @param month
	 *            O número do mês de 1:Janeiro até 12:Dezembro.
	 * @param day
	 *            O inteiro do dia dentro do mês (1 a 31).
	 */
	public Datex(int year, int month, int day) {
		Calendar calendar = new GregorianCalendar(year, month - 1, day);
		super.setTime(calendar.getTimeInMillis());
		useDate = true;
		useTime = false;
	}

	public Datex(long timeInMillis) {
		super(timeInMillis);
		useDate = true;
		useTime = true;

	}

	public DateFormat getDateFormat() {
		if (useDate) {
			if (useTime) {
				return DateFormat.getDateTimeInstance();
			} else {
				return DateFormat.getDateInstance();
			}
		} else {
			if (useTime) {
				return DateFormat.getTimeInstance();
			} else {
				return DateFormat.getDateInstance();
			}
		}
	}

	// ********** Aritimética de Datas **********

	/**
	 * Retorna quantos dias faltam da data atual pára a data passada como
	 * parâmetro. Se a data passada for anterior á data atual será retornado um
	 * número negativo.
	 * 
	 * @param date
	 *            A data de comparação sobre a qual será feita a diferença.
	 * @return A diferença em dias
	 */
	public long daysTo(Date date) {
		long dif = date.getTime() - getTime();
		return dif / MILIS_IN_ONE_DAY;
	}

	// ********** Override de Date **********

	@Override
	public void setTime(long time) {
		LOGGER.warning(DATEX_CANNOT_CHANGE);
	}

	@Override
	public void setDate(int date) {
		LOGGER.warning(DATEX_CANNOT_CHANGE);
	}

	@Override
	public void setHours(int hours) {
		LOGGER.warning(DATEX_CANNOT_CHANGE);
	}

	@Override
	public void setMinutes(int minutes) {
		LOGGER.warning(DATEX_CANNOT_CHANGE);
	}

	@Override
	public void setMonth(int month) {
		LOGGER.warning(DATEX_CANNOT_CHANGE);
	}

	@Override
	public void setSeconds(int seconds) {
		LOGGER.warning(DATEX_CANNOT_CHANGE);
	}

	@Override
	public void setYear(int year) {
		LOGGER.warning(DATEX_CANNOT_CHANGE);
	}

	@Override
	public String toString() {
		return asFormatted();
	}
	
	// ********** GET / SET **********

	public boolean isUseDate() {
		return useDate;
	}

	public boolean isUseTime() {
		return useTime;
	}

	public String asFormatted() {
		return getDateFormat().format(this);
	}

	

}
