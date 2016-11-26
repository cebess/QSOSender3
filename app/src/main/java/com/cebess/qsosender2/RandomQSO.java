package com.cebess.qsosender2;

/**
 * Created by chasb on 11/26/2016.
 */
import java.io.*;
import java.text.*;
import android.util.Log;

/**
 * RandomQSO defines a grammar that generates random amateur radio QSO's.
 * It is based on a similar grammar published by Paul J. Drongowski (NEOQT)
 * in his SuperiorMorse program. As well as a program by Martin Minow
 *
 *
 * Copyright &copy; 2016
 *      <a href="mailto:cebess@hotmail.com">Charles Bess</a>.
 * </p>
 * <p>
 * </p>
 *
 * @version 0.1
 * .
 */
public class RandomQSO extends RandomSentence
{
    private static final String noviceLicense   = "Novice";
    private static final String techLicense     = "Technician";
    private static final String generalLicense  = "General";
    private static final String advancedLicense = "Advanced";
    private static final String extraLicense    = "Extra";
    private static final String[] licenseClasses = {
            noviceLicense,
            techLicense,
            generalLicense,
            advancedLicense,
            extraLicense
    };
    private static final String[] callSignFormat = {
            "<Call2x3>",
            "<Call2x3>",
            "<Call1x3>",
            "<Call1x3>",
            "<Call2x2>",
            "<ExtraCallSign>"
    };

    private static final String grammar =
            "<Transceiver> Collins <Collins> | Drake <Drake> | Elecraft <Elecraft>"
                    + " | Hallicrafters <Hallicrafters> | Heathkit <Heathkit> | Icom <Icom>"
                    + " | Kenwood <Kenwood> | National <National> | Alinco <Alinco> "
                    + " | Swan <Swan> | Ten Tec <TenTec> | Yaesu <Yaesu> | Homebrew\n"
                    + ""
                    + "<Elecraft> K3 | KX2\n"
                    + "<Collins> 718U-5M | KWM-2\n"
                    + "<Drake> TR-3 | TR-4 | TR-4C | TR-5| TR-7\n"
                    + "<Hallicrafters> SR150 | SR160 | SR400 | SR500 | SR2000\n"
                    + "<Heathkit> DX60 w/Knight R-100A | HW7 | HW16 | HW32 | HW12A | HW22A | HW101 | SB102 | SB401 & SB 303\n"
                    + "<Icom> IC275 | IC475 | IC575 | IC7300 | IC726 | IC730 | IC745 | IC=756 | IC7100 | IC7600 | IC7700\n"
                    + "<Kenwood> TS-480SAT | TS-480HX| TS-590SG | TS-990S | TS-2000 | TR450 | TR751 | TR850 | TR851\n"
                    + "<National> NCX3 | NCX5 | NCL 200\n"
                    + "<Swan> 120 | 140 | 180 | 240 | 350\n"
                    + "<TenTec> 585 | 562 | Delta | Argonaut | Omni\n"
                    + "<Yaesu> FT891 | FT450D | FT991A | FT=757 | FT=767 | FTDX1200 | FTDX3000\n"
                    + "<Alinco> DX-SRST | DX-SR8T\n"
                    + ""
                    + "<Antenna> beam | delta loop | dipole | double zepp | half wave dipole"
                    + " | inverted V | whip | delta loop | parasitic beam | log periodic"
                    + " | quad loop | quad vertical | quagi | rhombic | longwire | mobile screwdriver"
                    + " | trap doublet | yagi | zepp | monobander | tribander | symmetrical delta loop"
                    + " | 3 element beam | 5 band vertical | 5 element loop | single band half wave dipole"
                    + " | lonogwire withan autotuner | cubical quad | vertical with 10 radials | dipole in the attic"
                    + " | whip with an autotuner | 7 band beam | G5RV | Buddipole \n"
                    + ""
                    + "<UpFeet> <OneToTen><ZeroOrFive>\n"
                    + "<OneToTen> 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10\n"
                    + "<ZeroOrFive> 0 | 5\n"
                    + "<Weather> sunny | rain | freezing rain | sleet | snow"
                    + " | cloudy | partly cloudy | partly sunny | clear"
                    + " | cold and windy | raining | snowing | sunny | hot"
                    + " | windy and warm | cloudy | drizzling | foggy | wet/foggy"
                    + " | fog/drizzle | hot/muggy | hot/dry | cool/windy | smoggy"
                    + " | hot/smoggy | cold/dry | hot/humid | warm | windy"
                    + " | very hot | very cold | very windy | wet/windy | humid | hot and humid\n"
                    + ""
                    + "<Power> 5 | 10 | 20 | 25 | 40 | 50 | 80 | 100 | 125 | 140"
                    + " | 150 | 170 | 200 | 250 | 270 | 300\n"
                    + "<Job> engineer | nurse | fireman | mechanic | programmer | carpenter"
                    + " | electrician | writer | teacher | doctor | attorney | lawyer"
                    + " | clerk | [2] chemist | librarian | teller | web designer | physicist"
                    + " | mathematician | professor | driver | milkman | gardener"
                    + " | bricklayer | guard | dentist | curator | farmer | stock broker"
                    + " | letter carrier | designer | student | college student"
                    + " | high school teacher | administrator | police officer"
                    + " | investment banker | politician | xray technician\n"
                    + "<Name> Al | Alan | Alice | Allen | Alex | Alexeev | Amber | Anne"
                    + " | Art | Barbara | Bart | Betty | Bea | Bill | Bob | Bruce | Bud"
                    + " | Carl | Carol | Cathy | Cheryl | Chris | Christy | Chuck | Dale"
                    + " | Dave | David | Dennis | Diane | Dick | Dan | Don | Ed | Elaine"
                    + " | Ellen | Francie | Fred | Gary | Helen | Ingrid | Frank | George"
                    + " | Gilda | Gus | Harry | Henry | Jack | James | Jane | Janet"
                    + " | Jeff | Jessica | Jill | Jim | Joan | Joe | John | Jon | Kathy"
                    + " | Kevin | Karen | Karl | Keith | Ken | Kent | Kristen | Kurt"
                    + " | Larry | Lauren | Liholiho | Linda | Lou | Lynda | Lynn"
                    + " | Mark | Margaret | Marv | Maria | Mark | Martin | Marty | Mary"
                    + " | Mike | Monica | Nancy | Neil | Noelani | Oliver | Olivia | Pat"
                    + " | Patrick | Paul | Paula | Peter | Phil | Ralph | Ray | Rex"
                    + " | Rich | Rick | Roy | Ron | Sally | Sam | Scott | Scottie"
                    + " | Spencer | Steve | Stu | Sue | Terry | Tim | Todd | Tony"
                    + " | Thomas | Walt | Wendy | William | Zelda\n"
                    + "<AnyCallSign> <Call2x3> | [5] <Call1x3> | <Call2x2> | <Call1x2> | <Call2x1>"
                    + " | G<Digits><Letters><Letters><Letters>"
                    + " | VE<Digits><Letters><Letters><Letters>\n"
                    + "<ExtraCallSign> <Call1x2> | <Call2x1>\n"
                    + "<Call2x3> <AKWN><Letters><Digits><Letters><Letters><Letters>\n"
                    + "<Call1x3> <AKWN><Digits><Letters><Letters><Letters>\n"
                    + "<Call2x2> <AKWN><Letters><Digits><Letters><Letters>\n"
                    + "<Call1x2> <AKWN><Digits><Letters><Letters>\n"
                    + "<Call2x1> <AKWN><Letters><Digits><Letters>\n"
                    + "<AKWN> A | K | W | N \n"
                    + "<Digits> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9\n"
                    + "<Letters> A | B | C | D | E | F | G | H | I | J | K | L"
                    + " | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z\n"
                    + "<LicenseClass>"  /* These strings will be used to generate the license class */
                    +   noviceLicense
                    + " | [2] "     + techLicense
                    + " | [4] "     + generalLicense
                    + " | "         + advancedLicense
                    + " | [3]"         + extraLicense
                    + "\n"
                    + "<City> Aiea | Alexander | Asbury | Baker | Beckley | Bedford"
                    + " | Berkeley | Brunswick | Brunsville | Chicago | Cambridge"
                    + " | Charleston | Circleville | Clarksburg | Clear Lake | Cleveland"
                    + " | Crystal | Duncanville | Elizabeth | Ewa | Fairbanks | Fairfield"
                    + " | Flint | Gahanna | Grant | Greensburg | Harper | Honolulu "
                    + " | Hillsdale | Jamestown | Jefferson | Kaaawa | Kona"
                    + " | Lawrenceville | Lakewood | Lincoln | Litchfield | London"
                    + " | Long Beach | Lyndhurst | Lynnville | Mansfield | Maple | Medows"
                    + " | Mentor | Mercer | Mewquite | Midland | Milldale | Milltown"
                    + " | Moorestown | Mountain View | Murray | Newfield | Newport"
                    + " | New London | Olmstead | Oak | Oxnard | Oxford | Paradise"
                    + " | Paris | Perry | Peru | Potter | Pottsville | Redwood | Russellville"
                    + " | Salem | Sandy | Saratoga | Smithville | Springdale | Springfield"
                    + " | Starkville | Sunnyvale | Tinker | Trenton | Walnut Creek "
                    + " | Warsaw | Washington | Weston | Wheatfield | Williamson"
                    + " | Worchester | Zion"
                    + " | [23] <New> <NewCity> | New York City"
                    + " | [40] <CityHeights> <Heights>\n"
                    + "<NewCity> Albany | Avalon | Barnard | Brunswick | Bedford"
                    + " | Chester | Conway | Dime Box | Franklin | Granville"
                    + " | Hamilton | London | Morris | Oxford | Salisbury | Stafford"
                    + " | Stanton | Trenton | Troy | Walpole | Warren | Weston"
                    + " | Windsor\n"
                    + "<CityHeights> Apple | Ashford | Baker | Baldwin | Banner"
                    + " | Barnard | Benton | Carson | Chester | Conway "
                    + " | Cornwall | Crystal | Fletcher | Franklin | Granite"
                    + " | Crant | Harper | Jefferson | Hamilton | Hickory"
                    + " | Lincoln | Maple | Mercer | Morgan | Morris | Murray"
                    + " | Oak | Orwell | Quail | Perry | Potter | Salem | Stafford"
                    + " | Stone | Tinker | Walnut | Warren | Washington | Weston "
                    + " | Wilton\n"
                    + "<New> New | Old | North | South | East | West\n"
                    + "<Heights> Castle | Heights | Island | Valley | City | Creek | Park | Mill | Neck\n"
                    + "<Quality> Outstanding |very good | Good | OK | Poor | very poor\n"
                    + "<MustQRT> Lots of QRM."
                    + " | Lots of QRM. I cant hear you"
                    + " | QRT for dinner."
                    + " | Must QRT for dinner."
                    + " | Must QRT for a nap."
                    + " | Must QRT for tea"
                    + " | Must QRT wife has something for me to do."
                    + " | Must QRT for lunch."
                    + " | Must QRT for a snack"
                    + " | Must QRT for sleep."
                    + " | Must QRT and take a break"
                    + " | Must QRT for bathroom break."
                    + " | Must QRT lightning threatens."
                    + " | Must QRT lightning storm has started."
                    + " | Must QRT tornado sirens sounding."
                    + " | Must QRT tsunami sirens sounding."
                    + " | Must QRT to hear news about approaching hurricane."
                    + " | Must QRT to check email."
                    + " | Must QRT weather radio alarm just went off."
                    + " | [5] Must QRT for sked with <Someone>."
                    + " | Must QRT <Someone> is at the front door."
                    + " | QRM I cant make you out of the other signals | QRM? the band is crowded"
                    + " | QRN I cant pull you out of the noise | QRN? the band is noisy <Here>"
                    + " | QSB the band is a bit unstable | QSB? band seems to be changing"
                    + " | QRS pse I cant copy that fast | QRS? I can slow down"
                    + " | QSY? there is QRM on this freq | QSK? in case I need to interupt you | QRX? so we can work again | QTH? | QTR? My clock is broken"
                    + " | What is your QTH?\n"
                    + "<Someone> my uncle | my brother | my sister | my mom\n"
                    + "<States> AL | AK | AZ | AR | CA | CO | CT | DE | FL | GA | HI | ID | IL | IN | IA | KS | KY | LA | ME | MD | MA | MI | MN | MS | MO | MT | NE | NV"
                    + " | NH | NJ | NM | NY | NC | ND | OH | OK | OR | PA { RI | SC | SD | TN | TX | UT | VT | VA | WA | WV | WI | WY \n"
                    + "<StatesLong> Alabama | Alaska | Arizona | Arkansas | California"
                    + " | Colorado | Connecticut | Delaware | Florida | Gaum | Georgia"
                    + " | Hawaii | Idaho | Illinois | Indiana | Iowa | Kansas | Kentucky"
                    + " | Louisiana | Maine | Maryland | Massachusetts | Michigan | Midway"
                    + " | Minnesota | Mississippi | Missouri | Montana | Nebraska | Nevada"
                    + " | New Hampshire | New Jersey | New Mexico | New York | North Carolina"
                    + " | North Dakota | Ohio | Oklahoma | Oregon | Pennsylvania | Puerto Rico"
                    + " | Rhode Island | Saipan | American Samoa | South Carolina | South Dakota"
                    + " | Tennessee | Texas | Utah | Vermont | Virginia | Virgin Islands"
                    + " | Wake Island | Washington | West Virginia | Wisconsin | Wyoming\n"
                    + "<Miscellaneous> I sometimes work DSTAR on vhf."
                    + " | I usually work APRS on vhf."
                    + " | [2] We are on vacation and I am mobile."
                    + " | Your signal is chirpy."
                    + " | Is it green there or brown?"
                    + " | Do you have antenna restrictions?"
                    + " | Where do you live?"
                    + " | Do you ever run mobile?"
                    + " | How long have you been a ham?"
                    + " | How long have you been a radio amateur?"
                    + " | What are your other hobbies?"
                    + " | What part of ham radio do you like best?"
                    + " | I am mobile and am going to the office."
                    + " | I am mobile and am going to work."
                    + " | I am mobile and driving to work."
                    + " | I am mobile, driving home from work."
                    + " | I am mobile, stuck in traffic."
                    + " | I am mobile, driving cross country."
                    + " | I am mobile, riding a bike."
                    + " | How is my signal?"
                    + " | QSL?"
                    + " | I am feeling <Quality> today. How about you?"
                    + " | How is your WX?"
                    + " | How copy so far?"
                    + " | How copy?"
                    + " | Tnx for QSO"
                    + " | Do you ever use an iambic keyer?"
                    + " | Cpy?"
                    + " | Tnx for the report."
                    + " | Thanks for the call."
                    + " | Tnx for the call."
                    + " | Do you ever run SSB?"
                    + " | Do you operate PSK?"
                    + " | Have you ever foxhunted on VHF?"
                    + " | Have you ever been to <StatesLong>?"
                    + " | Do you ever use a straight key?"
                    + " | What is your job?"
                    + " | Whats your job?"
                    + " | How is the weather there?"
                    + " | Hows the weather?"
                    + " | How is the weather?"
                    + " | What kind of power supply are u using?"
                    + " | ru using linear amplifier?"
                    + " | I sometimes use a linear amplifier."
                    + " | Are you using a linear amplifier?"
                    + " | Using a linear amplifier?"
                    + " | [5] Propagation is <Quality>."
                    + " | Are you on daylight savings time?"
                    + " | Did you hear the news?"
                    + " | Did you hear any news?"
                    + " | Is the bad news true?"
                    + " | Sorry, just pressed the wrong macro button"
                    + " | There is an eclipse of the moon right now."
                    + " | Do you recycle often?"
                    + " | Do you prefer metric units? I seem to be using them more"
                    + " | We just felt a small quake <Here>."
                    + " | Whoa, we are having an earthquake <Here>. A real roller."
                    + " | You are my first contact today."
                    + " | You are my second contact today."
                    + " | You are my last contact today."
                    + " | Are you married?"
                    + " | Do you have a horse?"
                    + " | Do you have a dog?"
                    + " | Do you have a cat?"
                    + " | Do you have a pet fish?"
                    + " | Do you have a snake?"
                    + " | A clumsy cat just stepped on my hand."
                    + " | How old is your rig?"
                    + " | How did you learn morse code?"
                    + " | How fast can you send morse code?"
                    + " | When did you start to learn morse code?"
                    + " | Are you <Quality> at calculus? I have a question for you. HIHI"
                    + " | Are you <Quality> at antenna design? I have a question for you."
                    + " | Have you hung an antenna in a tree? That is supposed to be easy."
                    + " | Do you own a 3D printer?"
                    + " | Been a ham for <1to3> years how about you?"
                    + " | Have you ever ran a net?"
                    + " | There is a rainbow outside the window."
                    + " | I just saw a bolide. Have you ever worked meteor shower?"
                    + " | Do you know the word copacetic, if so thats excellent."
                    + " | Are you a ragchewer? Do you know the definition of the word garralous?"
                    + " | What time zone are you in?"
                    + " | Would you have given Morse a Nobel prize?"
                    + " | Our neighbors have a horse named morse."
                    + " | What is your elevation?"
                    + " | What is the air pressure there?"
                    + " | I sometimes monitor ULF for earthquake precursors."
                    + " | Can you think of something else that I should know about?"
                    + "\n"
                    + "<RSTDigits> [25] 5<5To9><5To9> | 5nn | 354 | 248 | 354\n"
                    + "<5To9> 5 | 6 | 7 | 8 | 9\n"
                    + "<1To3> | 1 | 2 | 3\n"
                    + "<Here> here | hr |\n"
                    + "<is> is | es |\n"
    /* Here are some larger chunks */
                    + "<MyThanks> Thanks for your call. | Tnx for ur call. | Tnx for the call OM."
                    + " | Thanks for the call. | Thanks <Name> for the call.\n"
                    + "<MyName> Name <is> <Name>. | Name <Name> <Name>. | Name hr <is> <Name>."
                    + " | My name <is> <Name>.\n"
                    + "<MyJob> I am a <Job>. | I work as a <Job>. | I was a <Job>, now retired."
                    + "| I was a <Job> but am now retired.| I was a <Job> but now looking to change jobs.\n"
                    + "<MyAge> I am <1To8><Digits> years old."
                    + " | My age <is> <1To8><Digits>."
                    + " | age <1To8><Digits>."
                    + " | Age <is> <1To8><Digits>.\n"
                    + "<1To8> 1 | 2 | 3 | 4 | 5 | 8 | 7 | 8\n"
                    + "<MyLicense> I have a <SenderLicense> license."
                    + " | I am a <SenderLicense> license ham."
                    + " | I have had a <SenderLicense> license for <Digits> year."
                    + " | I have been a <SenderLicense> for <5To9> years."
                    + " | I have had a <SenderLicense> license for <1To3><Digits> years.\n"
                    + "<MyTemperature> Temp hr is <Temperature>"
                    + " | Temp is <Temperature> |\n"
                    + "<MyTemperatureLC> temp <Here> is <Temperature>"
                    + " | temp is <Temperature> |\n"
                    + "<MyWeather> <WeatherText> <MyTemperatureLC>."
                    + " | <MyTemperature> <WeatherText>."
                    + " | Temp is <Temperature> degrees <Here> and <WeatherText>."
                    + " | <Weather> <Here> and <Temperature> degrees.\n"
                    + "<WeatherText> <WeatherName> is <Weather>"
                    + " | <WeatherName> <Here> is <Weather> <Wind>"
                    + " | It is <Weather> <Here> <Wind>"
                    + " | It is <Weather>\n"
                    + "<WeatherName> Weather | WX\n"
                    + "<Wind> wind is <1To3><Digits> mph from the <Direction>\n"
                    + "<Direction> North | South | East | West | NNW | SSE | NNE } SSW\n"
                    + "<Temperature> <1To3><Digits> <TempUnits>\n"
                    + "<TempUnits> F | C |\n"
                    + "<CityState> <City>, <States> | <City> <StatesLong>\n"
                    + "<MyLocation> location is <CityState>."
                    + " | My QTH is <CityState>."
                    + " | QTH is <CityState>. | QTH <CityState>\n"
                    + "<MyRig> My rig runs <Power> watts into a <Antenna> up <UpFeet> ft "
                    + " | Rig is <Transceiver> running <Power> watts, antenna is a <Antenna> "
                    + " | My rig is <Transceiver>. It runs <Power> watts into a <Antenna> My antenna is up <UpFeet> feet."
                    + " | Rig <Here> is <Transceiver> running <Power> watts into a <Antenna> up <UpFeet> ft.\n"
                    + "<YourRST> <Your> <Signal> <is> <RST> <RST>.\n"
                    + "<Your> Your | UR |\n"
                    + "<Signal> RST | Signal\n"
                    + "<CallSigns> <Receiver> de <Sender>\n"
                    + "<Opt73> [2] 73 | 73 and tnx for QSO. | Gud DX 73 | 73 c u down the log | 73 later | Good DX 73\n"
                    + "<LongQSOText> <YourRST> <MyName> <MyLocation> <Miscellaneous>"
                    +           " <MyRig> <MyWeather> <MyJob> <MyAge> <MyLicense>"
                    + " | <MyLocation> <YourRST> <MyRig> <MyWeather> <Miscellaneous>"
                    +           " <MyName> <MyLicense> <MyAge> <MyJob>"
                    + " | <MyThanks> <YourRST> <MyName> <MyWeather> <MyLocation>"
                    +           " <MyJob> <MyLicense> <MyRig> <MustQRT> <MyAge>"
                    + " | <MyLocation> <YourRST> <MyRig> <Miscellaneous> <MyName>"
                    +           " <MyAge> <MyJob> <MyLicense> <MyWeather> <MustQRT> "
                    + " | <MyThanks> <YourRST> <MyJob> <Miscellaneous> <Miscellaneous>"
                    +           " <MyName> <MyAge> <MyLicense>"
                    +           " <MyRig> <MyLocation> <MyWeather> <Miscellaneous> <MustQRT> "
                    + " | <MyLocation> <YourRST> <MyRig> <MyName> <MyJob> <MyAge>"
                    +           " <Miscellaneous> <MyLicense> <MyWeather>"
                    +           " <Miscellaneous>\n"
                    + "<MediumQSOText> <YourRST> <MyName> <MyLocation> <MyRig> "
                    + " | <MyName> <MyAge> <YourRST> <MyRig> <MyLocation> "
                    + " | <YourRST> <MyName> <MyAge> <MyLocation> <MyRig> "
                    + " | <YourRST>  <MyRig> <MyName> <MyAge> <Miscellaneous> "
                    + " | <YourRST>  <MyRig> <MyName> <MyLocation> <Miscellaneous> "
                    +   "\n"
                    + "<ShortQSOText> <YourRST> <MyName> <MyRig> "
                    + " | <MyName> <YourRST> <MyRig> "
                    + " | <MyName> <YourRST> <MyLocation>"
                    + " | <MyName> <YourRST> <MyRig> <MyLocation>"
                    + " | <YourRST> <MyName> <MyAge> <MyRig> "
                    + " | <YourRST>  <MyRig> <MyName> <MyAge> "
                    + " | <YourRST>  <MyRig> <MyName> <MyLocation> "
                    +   "\n"
      /* LongQSO is for 15 WPM or faster QSO's */
                    + "<LongQSO> <CallSigns> <LongQSOText> <Opt73> <CallSigns> kn\n"
      /* MediumQSO is for 10 WPM to 15 WPM */
                    + "<MediumQSO> <CallSigns>  <MediumQSOText> <MustQRT> <Opt73>  <CallSigns> kn\n"
      /* ShortQSO is for < 10 WPM */
                    + "<ShortQSO> <CallSigns> <ShortQSOText> <Opt73> <CallSigns> k\n"
     /* RandomDigits returns a 5-character "word" consisting of digits */
                    + "<RandomDigits> <Digits><Digits><Digits><Digits><Digits>\n"
     /* Random2Element returns a 5-character "word" using the 1 and 2-element symbols */
                    + "<Random2Element> <TwoElem><TwoElem><TwoElem><TwoElem><TwoElem>\n"
                    + "<TwoElem> A | E | I | M | N | T\n"
     /* Random3Element returns a 5-character "word" using the 3-element symbols */
                    + "<Random3Element> <ThreeElem><ThreeElem><ThreeElem><ThreeElem><ThreeElem>\n"
                    + "<ThreeElem> D | G | K | O | R | S | U | W\n"
     /* Random4Element returns a 5-character "word" using the 3-element symbols */
                    + "<Random4Element> <FourElem><FourElem><FourElem><FourElem><FourElem>\n"
                    + "<FourElem> B | C | F | H | J | L | P | Q | V | X | Y | Z\n"
     /* RandomPunct returns a 5-character "word" using the punctuation and prosigns */
                    + "<RandomPunct> <RandomPunct><RandomPunct><RandomPunct><RandomPunct><RandomPunct>\n"
                    + "<RandomPunct> . | , | / | ? | = | + | #\n"
    /* These will be replaced for each QSO */
                    + "<SenderLicense> <LicenseClass>\n"
                    + "<Sender> <AnyCallSign>\n"
                    + "<Receiver> <AnyCallSign>\n"
                    + "<RST> <RSTDigits>\n"
            ;

    /**
     * Create a grammar that can generate random QSO's.
     */
    public RandomQSO()
            throws ParseException, IOException
    {
        super(grammar);
        if (MainActivity.DEBUG && checkAllSymbols() > 0) {
            writeRules();
        }
    }

    /**
     * Return a random QSO as a string.
     * @return the QSO as a string.
     */
    public String getQSO(int wpm)
    {
        /*
         * Populate the sender, receiver, and RST
         */
        String senderLicense    = expand("<LicenseClass>");
        String callSign         = callSignFormat[0];
        for (int i = 0; i < licenseClasses.length; i++) {
            if (senderLicense.equals(licenseClasses[i])) {
                callSign        = callSignFormat[i];
                break;
            }
        }
        Log.d(MainActivity.ProjectName,"callSign: " + callSign);
        String sender           = expand(callSign);
        Log.d(MainActivity.ProjectName,"sender: " + sender);
        String receiver;
        do {
            receiver    = expand("<AnyCallSign>");
        } while (receiver.equals(sender));
        String RST      = expand("<RSTDigits>");
        /*
         * Store these back into the main grammar.
         */
        try {
            addRule("<SenderLicense> " + senderLicense);
            addRule("<Sender> " + sender);
            addRule("<Receiver> " + receiver);
            addRule("<RST> " + RST);
        }
        catch (Exception e) {
            Log.e(MainActivity.ProjectName,"Can't compile QSO: " + e.toString());
        }
        String result;
        if (wpm < 10) {
            result              = expand("<ShortQSO>");
        }
        else if (wpm < 15) {
            result              = expand("<MediumQSO>");
        }
        else {
            result              = expand("<LongQSO>");
        }
        return (result);
    }
}
