package JavaSources.utilities;

public class Converter {

	public boolean isNumber(Object obj) {
		boolean ret = true;
		int i;

		for (i = 0; i < obj.toString().length(); i++) {
			if (!isDigit(obj.toString().charAt(i))) {
				ret = false;
				break;
			}
		}
		return ret;
	}

	private boolean	isDigit( char c )		{
		boolean	ret		= false;

		switch( c )		{
		case '0': ret	= true;	break;
		case '1': ret	= true;	break;
		case '2': ret	= true;	break;
		case '3': ret	= true;	break;
		case '4': ret	= true;	break;
		case '5': ret	= true;	break;
		case '6': ret	= true;	break;
		case '7': ret	= true;	break;
		case '8': ret	= true;	break;
		case '9': ret	= true;	break;

		default: ret	= false;
		}
		return ret;
	}
}
