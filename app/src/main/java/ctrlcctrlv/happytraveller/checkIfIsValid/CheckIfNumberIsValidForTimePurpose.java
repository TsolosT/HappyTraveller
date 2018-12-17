package ctrlcctrlv.happytraveller.checkIfIsValid;

public class CheckIfNumberIsValidForTimePurpose
{
    int integerPart;
    int decimalPart=0;

    public int theNumberUserGaveIs(String usersNumber)
    {
        boolean theValidityOfNumberIs = true;


        if (usersNumber.indexOf('.') != -1)
        {
            String decimalPartStringForm = usersNumber.substring(usersNumber.indexOf('.')+1);
            String integerPartStringForm = usersNumber.substring(0,usersNumber.indexOf('.'));

            if (integerPartStringForm.equals(""))
            {
                integerPart = 0;
            }else
            {
                integerPart =  Integer.parseInt(integerPartStringForm);
            }

            decimalPart = Integer.parseInt(decimalPartStringForm);

            if (integerPart > 12)
                theValidityOfNumberIs = false;
            if (integerPart < 0)
                theValidityOfNumberIs = false;
            if (decimalPart <0)
                theValidityOfNumberIs = false;
            if (decimalPart > 59)
                theValidityOfNumberIs = false;
        }
        else
        {
            integerPart = Integer.parseInt(usersNumber);

            if (integerPart >= 13)
                theValidityOfNumberIs = false;

            if (integerPart == 0)
                theValidityOfNumberIs = false;
        }

       // System.out.println(theValidityOfNumberIs);
        if (theValidityOfNumberIs)
            return integerPart*60+decimalPart;
        else
            return 0;

    }
}
