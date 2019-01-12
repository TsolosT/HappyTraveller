package ctrlcctrlv.happytraveller.checkIfIsValid;



public class CheckIfNumberIsValidForTimePurpose
{
    int integerPart;
    int decimalPart=0;

    /**
     * Checks if the number that user gave is valid in order for application to give a valid suggestion about witch sights user can visit
     *
     * @param usersNumber
     * @return if the number is valid returns how many seconds users has to spend else returns 0
     */
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

        if (theValidityOfNumberIs)
            return integerPart*60+decimalPart;
        else
            return 0;

    }
}
