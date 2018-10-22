
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import javax.swing.JFileChooser;

import com.google.gson.*;


/**
 * Write a description of class Character here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Character
{
    private Hashtable<String,int[]> skills = new Hashtable<String, int[]>();
    private static ArrayList<String> statList = new ArrayList<String>();
    
    //private static String[] statArrayList= {"strSkills","percSkills","dexSkills","knowSkills","techSkills","mechSkills"};
    
    static{
    	statList.add("Perception");statList.add("Dexterity");statList.add("Strength");
    	statList.add("Knowledge");statList.add("Technical");statList.add("Mechanical");
    	statList.add("Alter");statList.add("Control");statList.add("Sense");
    	}
    
    private String characterName;
    private int characterPoints;
    private int skillsCharacterPoints;
    //private int statPoints;		//Unused stat for this build
    //private int startingD;		//Unused stat for this build
       
    private int[] dex = {0,0};
    private int[] perc = {0,0};
    private int[] know = {0,0};
    private int[] str = {0,0};
    private int[] mech = {0,0};
    private int[] tech = {0,0};
    private int[] control = {0,0};
    private int[] sense = {0,0};
    private int[] alter = {0,0};
    
    private String[] dexSkills = {"Archaic Guns","Blaster", "Blaster Artillery",
    "Bowcaster","Bows","Blindfighting","Brawling Parry","Contortions","Dance",
    "Dodge","Electroshock Pad","Energy Weapons","Firearms","Flamethrower",
    "Grenade","Lightsaber","Melee Combat","Melee Parry","Missile Weapons",
    "Pick Pocket","Pulsewave Weapons","Running","ShockballFling","Shockball Scoop",
    "Slingshots","Thrown Weapons","Vehichle Blasters","Wedsphere"};
    
    private String[] strSkills = {"Acrobatics","Brawling","Climbing/Jumping",
    "Digging","Flight","Lifting","Stamina","Swimming"};
    
    private String[] knowSkills = {"Accounting","Agriculture","Alien Species","Art",
    "Artist","Biochemisty","Brainwashing","Bureaurcracy","Business",
    "Business Administration","Culinary Arts","Cultures","Decontamination Process",
    "Drink Mixology","Ecology","Economics","Genetics","Heist Coordination",
    "Home Economics","Imperial Supplies","Intimidation","Languages","Law Enforcement",
    "Military History","Planetary Systems","Shockball","Streetwise","Survival",
    "Tactics","Technology","Traffic Control Procedures","Value","Willpower"};
    
    private String[] mechSkills = {"Airship Operations","Archaic Startship Piloting",
    "Astrogation","Bacta Tank Operations","Battle Station Piloting","Beast Handling",
    "Beast Riding","Capital Ship Gunnery","Capital Ship Piloting","Capital Ship Shields",
    "Carbon Freezing Chamber Operations","Communications","Energize Power Cells","Gliders",
    "Ground Vehicle Operation","Holorecorder Operations","Hover Vehicle Operation",
    "Jet Pack Operation","Machinery Operations","Musical Instrument Operation",
    "Nautical Vessel Piloting","Powersuit Operation","Repulserlift Operation",
    "Sailed Nautical Vessel Operations","Sensors","Space Transports","Starfighter Piloting",
    "Starship Gunnery","Starship Shields","Swoop Operation","Walker Operation",
    "Watercraft Operations","World Devestator Operations"};
        
    private String[] percSkills = {"Bargain","Command","Con","Cesa","Cleaning",
    "Forgery","Gambling","Hide","Injury Ailment Diagnosis","Investigation",
    "Persuasion","Search","Sneak"};
    
    private String[] techSkills = {"Aquatic Vehicle Engineering","Aquatic Vehicle Repair",
    "Archaic Gun Repair","Armor Design","Artillery Engineering","Battle Station Engineering",
    "Battle Station Repair","Battle Station Weapon Engineering ",
    "Battle Station Weapon Repair","Blaster Artillery Engineering",
    "Armor Repair","Blaster Repair","Blaster Engeneering","Bowcaster Repair",
    "Bow Repair","Capital Ship Engineering","Capital Starship Repair",
    "Capital Starship Weapon Repair","Communications Repair","Computer Engineering",
    "Computer Programming/Repair","Cyborg Technology","Demolition",
    "Droid Construction","Droid Engineering","Droid Programming",
    "Encryption","Engineering","Farm Equipment Repair","Firearm Repair",
    "First Aid","Genetic Engineering","Ground Vehicle Repair",
    "Holorecorder Repair","Hover Vehicle Repair","Lightsaber Repair",
    "Machinery Repair","Melee Weapon Construction","Melee Weapon Repair",
    "Medicine","Metallurgy","Melee Weapon Construction","Moisture Farm Technology",
    "Musical Instrument Repair","Powersuit Repair","Prosthetic Design",
    "Prosthetic Repair","Pulse-Wave Weapon Repair","Repulsorlift Engineering",
    "Repulosorlift Construction","Repulsorlift Repair","Security",
    "Space Transports Engineering","Space Transports Repair","Starfighter Engineering",
    "Starfighter Repair", "Starship Weapon Engineering","Starship Weapon Repair",
    "Systems Diagnosis","Walker Construction","Walker Engineering","Walker Repair",};
    
    /**
     * Constructor for objects of class Character
     */
    public Character(String characterName, int dexDie, int dexPip, int strDie, int strPip, int percDie, int percPip, int knowDie, int knowPip, int mechDie, int mechPip, int techDie, int techPip)
    {
    	String fileInputString = getFileName();
        this.characterName = characterName;   
        characterPoints = 0;
        skillsCharacterPoints = 0;
        //statPoints = 0;
        //startingD = 0;
        
        dex[0] = dexDie; dex[1] = dexPip;
        perc[0] = percDie; perc[1] = percPip;
        know[0] = knowDie; know[1] = knowPip;
        str[0] = strDie; str[1] = strPip;
        mech[0] = mechDie; mech[1] = mechPip;
        tech[0] = techDie; tech[1] = techPip;
        
        for(int i = 0; i < percSkills.length; i++)
        {
            skills.put(percSkills[i],new int[]{perc[0],perc[1]});
        }
        
        for(int i = 0; i < strSkills.length; i++)
        {
            skills.put(strSkills[i],new int[]{str[0],str[1]});
        }
        
        for(int i = 0; i < dexSkills.length; i++)
        {
            skills.put(dexSkills[i],new int[]{dex[0],dex[1]});
        }
        
        for(int i = 0; i < knowSkills.length; i++)
        {
            skills.put(knowSkills[i],new int[]{know[0],know[1]});
        }
        
        for(int i = 0; i < mechSkills.length; i++)
        {
            skills.put(mechSkills[i],new int[]{mech[0],mech[1]});
        }
        for(int i = 0; i < techSkills.length; i++)
        {
            skills.put(techSkills[i],new int[]{tech[0],tech[1]});
        }
        
        readFromFile(fileInputString);
    }
    public void listSkills()
    {
        System.out.println("\nPerception: " + perc[0] + "D+" + perc[1]);        
        for(int i = 0; i < percSkills.length; i++)
        {   
            if(getDieDoubleRep(skills.get(percSkills[i])[0],skills.get(percSkills[i])[1]) != getDieDoubleRep(perc[0],perc[1]))
            {   
                System.out.println(percSkills[i] + ": " + skills.get(percSkills[i])[0]+ "D+" + skills.get(percSkills[i])[1]);
            }
        }
        System.out.println("\nDexterity: " + dex[0] + "D+" + dex[1]);
        for(int i = 0; i < dexSkills.length; i++)
        {
            if(getDieDoubleRep(skills.get(dexSkills[i])[0],skills.get(dexSkills[i])[1]) != getDieDoubleRep(dex[0],dex[1]))
            {   
                System.out.println(dexSkills[i] + ": " + skills.get(dexSkills[i])[0]+ "D+" + skills.get(dexSkills[i])[1]);
            }
        }
        System.out.println("\nStrength: " + str[0] + "D+" + str[1]);
        for(int i = 0; i < strSkills.length; i++)
        {
            if(getDieDoubleRep(skills.get(strSkills[i])[0],skills.get(strSkills[i])[1]) != getDieDoubleRep(str[0],str[1]))
            {   
                System.out.println(strSkills[i] + ": " + skills.get(strSkills[i])[0]+ "D+" + skills.get(strSkills[i])[1]);
            }
        }
        System.out.println("\nKnowledge: " + know[0] + "D+" + know[1]);
        for(int i = 0; i < knowSkills.length; i++)
        {
            if(getDieDoubleRep(skills.get(knowSkills[i])[0],skills.get(knowSkills[i])[1]) != getDieDoubleRep(know[0],know[1]))
            {   
                System.out.println(knowSkills[i] + ": " + skills.get(knowSkills[i])[0]+ "D+" + skills.get(knowSkills[i])[1]);
            }
        }
        System.out.println("\nMechanical: " + mech[0] + "D+" + mech[1]);
        for(int i = 0; i < mechSkills.length; i++)
        {
            if(getDieDoubleRep(skills.get(mechSkills[i])[0],skills.get(mechSkills[i])[1]) != getDieDoubleRep(mech[0],mech[1]))
            {   
                System.out.println(mechSkills[i] + ": " + skills.get(mechSkills[i])[0]+ "D+" + skills.get(mechSkills[i])[1]);
            }
        }
        System.out.println("\nTechnical: " + tech[0] + "D+" + tech[1]);
        for(int i = 0; i < techSkills.length; i++)
        {
            if(getDieDoubleRep(skills.get(techSkills[i])[0],skills.get(techSkills[i])[1]) != getDieDoubleRep(tech[0],tech[1]))
            {   
                System.out.println(techSkills[i] + ": " + skills.get(techSkills[i])[0]+ "D+" + skills.get(techSkills[i])[1]);
            }
        }
        
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    
    public int getCPCost(double dieDouble)
    {
    	int characterPointCost;
    	characterPointCost = (int) Math.round(1.5*dieDouble*dieDouble-1.4988*dieDouble+0.2723);
    	return characterPointCost;
    }
    public double getSkill(String skillName)
    {
        if(skills.containsKey(skillName)){
            return getDieDoubleRep(skills.get(skillName)[0],skills.get(skillName)[1]);
        }
        return 0.0;
    }
    public String getDieStringRep(String skillName)
    {
    	if(statList.contains(skillName)) {
    		if(skillName=="Perception"){
    			return (perc[0]+"D+"+perc[1]);
    		}
    		else if(skillName=="Dexterity") {
    			return (dex[0]+"D+"+dex[1]);
    		}
			else if(skillName=="Strength") {
				return (str[0]+"D+"+str[1]);
			 }
			else if(skillName=="Knowledge") {
				return (know[0]+"D+"+know[1]);
			}
			else if(skillName=="Technical") {
				return (tech[0]+"D+"+tech[1]);
			}
			else if(skillName=="Mechanical") {
				return (mech[0]+"D+"+mech[1]);
			}
			else if(skillName=="Alter") {
				return (alter[0]+"D+"+alter[1]);
			}
			else if(skillName=="Control") {
				return (control[0]+"D+"+control[1]);
			}
			else {
				return (sense[0]+"D+"+sense[1]);
			}
    	}
    
    	else {
    		return (skills.get(skillName)[0]+"D+"+skills.get(skillName)[1]);
    	}
    }
    
    public int getCPs()
    {
        return characterPoints;
    }
    
    public int getSkillsCPs()
    {
    	for(int i = 0; i < percSkills.length; i++)
        {   
            if(!Arrays.equals(skills.get(percSkills[i]),perc))
            {   
            	skillsCharacterPoints = skillsCharacterPoints 
            			+ ((getCPCost(getDieDoubleRep(skills.get(percSkills[i])[0],skills.get(percSkills[i])[1])))
            			- (getCPCost(getDieDoubleRep(perc[0],perc[1]))));
            }
        }
        
        //Checks dexterity skill deltas between this and other Character
        for(int i = 0; i < dexSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(dexSkills[i]),dex))
            {   
            	skillsCharacterPoints = skillsCharacterPoints 
            			+ ((getCPCost(getDieDoubleRep(skills.get(dexSkills[i])[0],skills.get(dexSkills[i])[1])))
            			-(getCPCost(getDieDoubleRep(dex[0],dex[1]))));
            }
        }
        
        //Checks strength skill deltas between this and other Character
        for(int i = 0; i < strSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(strSkills[i]),str))
            {   
            	skillsCharacterPoints = skillsCharacterPoints 
            			+ ((getCPCost(getDieDoubleRep(skills.get(strSkills[i])[0],skills.get(strSkills[i])[1])))
            			-(getCPCost(getDieDoubleRep(str[0],str[1]))));
            }
        }
        
        //Checks knowledge skill deltas between this and other Character
        for(int i = 0; i < knowSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(knowSkills[i]),know))
            {   
            	skillsCharacterPoints = skillsCharacterPoints 
            			+ ((getCPCost(getDieDoubleRep(skills.get(knowSkills[i])[0],skills.get(knowSkills[i])[1])))
            			-(getCPCost(getDieDoubleRep(know[0],know[1]))));
            }
        }
        
        //Checks mechanical skill deltas between this and other Character
        for(int i = 0; i < mechSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(mechSkills[i]),mech))
            {   
            	skillsCharacterPoints = skillsCharacterPoints 
            			+ ((getCPCost(getDieDoubleRep(skills.get(mechSkills[i])[0],skills.get(mechSkills[i])[1])))
            			-(getCPCost(getDieDoubleRep(mech[0],mech[1]))));
            }
        }
        
        //Checks technical skill deltas between this and other Character
        for(int i = 0; i < techSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(techSkills[i]),tech))
            {   
            	skillsCharacterPoints = skillsCharacterPoints 
            			+ ((getCPCost(getDieDoubleRep(skills.get(techSkills[i])[0],skills.get(techSkills[i])[1])))
            			-(getCPCost(getDieDoubleRep(tech[0],tech[1]))));
            }
        }
        
        return skillsCharacterPoints;
    }
    
    public int getCombatSkillsCPs()
    {
    	int combatCharacterPoints = 0;
    	for(int i = 0; i < percSkills.length; i++)
        {   
            if(!Arrays.equals(skills.get(percSkills[i]),perc))
            {   
            	combatCharacterPoints = combatCharacterPoints 
            			+ ((getCPCost(getDieDoubleRep(skills.get(percSkills[i])[0],skills.get(percSkills[i])[1])))
            			- (getCPCost(getDieDoubleRep(perc[0],perc[1]))));
            }
        }
        
        //Checks dexterity skill deltas between this and other Character
        for(int i = 0; i < dexSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(dexSkills[i]),dex))
            {   
            	combatCharacterPoints = combatCharacterPoints 
            			+ ((getCPCost(getDieDoubleRep(skills.get(dexSkills[i])[0],skills.get(dexSkills[i])[1])))
            			-(getCPCost(getDieDoubleRep(dex[0],dex[1]))));
            }
        }
        
        //Checks strength skill deltas between this and other Character
        for(int i = 0; i < strSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(strSkills[i]),str))
            {   
            	combatCharacterPoints = combatCharacterPoints 
            			+ ((getCPCost(getDieDoubleRep(skills.get(strSkills[i])[0],skills.get(strSkills[i])[1])))
            			-(getCPCost(getDieDoubleRep(str[0],str[1]))));
            }
        }

        
        return combatCharacterPoints;
    }
    
    public double getDieDoubleRep(int die, double pip)
    {
        return Math.round((die+pip/3) * 100d) / 100d;
    }
    public int[] getDieDoubleRepFromString(String skill)
    {
    	int[] dieValues = new int[2];
    	String[] splitDie = skill.split("D+");
    	String dieStringValue0 = splitDie[0];
    	String dieStringValue1 = splitDie[1];
    	splitDie[0] = dieStringValue0.substring(1);
    	splitDie[1] = dieStringValue1.substring(1,2);
    	dieValues[0] = Integer.parseInt(splitDie[0]);
    	dieValues[1] = Integer.parseInt(splitDie[1]);
    	
    	return dieValues;
    }
    
    
    
    public double getStatValue(String statName)
    {
    	String stat = statName.toLowerCase();
       if(stat.equals("perception"))
    	{
            //System.out.println("Perception: " + perc[0] + "D+" + perc[1]);
            return getDieDoubleRep(perc[0],perc[1]);
        }
        else if(stat.equals("dexterity"))
        {
            //System.out.println("Dexterity: " + dex[0] + "D+" + dex[1]);
            return getDieDoubleRep(dex[0],dex[1]);
        }
        else if(stat.equals("strength"))
        {
            //System.out.println("Strength: " + str[0] + "D+" + str[1]);
            return getDieDoubleRep(str[0],str[1]);
        }
        else if(stat.equals("knowledge"))
        {
            //System.out.println("Knowledge: " + know[0] + "D+" + know[1]);
            return getDieDoubleRep(know[0],know[1]);
        }
        else if(stat.equals("mechanical"))
        {
            //System.out.println("Mechanical: " + mech[0] + "D+" + mech[1]);
            return getDieDoubleRep(mech[0],mech[1]);
        }
        else if(stat.equals("technical"))
        {
            //System.out.println("Technical: " + tech[0] + "D+" + tech[1]);
            return getDieDoubleRep(tech[0],tech[1]);
        }
        else if(stat.equals("control"))
        {
            //System.out.println("Control: " + control[0] + "D+" + control[1]);
            return getDieDoubleRep(control[0],control[1]);
        }
        else if(stat.equals("sense"))
        {
            //System.out.println("Sense: " + sense[0] + "D+" + sense[1]);
            return getDieDoubleRep(sense[0],sense[1]);
        }
        else if(stat.equals("alter"))
        {
            //System.out.println("Alter: " + alter[0] + "D+" + alter[1]);
            return getDieDoubleRep(alter[0],alter[1]);
        }
        else{
            System.out.println("Try one of the following: Strength, Dexterity, Perception, Knowledge, Technical, Mechanical, Sence, Control, or Alter");
            return 0.0;
        }
    }
    
    public int getCharacterPoints(){
        int deltaCPs = 0; 
        
        //Checks perception skill deltas between this and other Character
        for(int i = 0; i < percSkills.length; i++)
        {   
            if(!Arrays.equals(skills.get(percSkills[i]),perc))
            {
                deltaCPs=deltaCPs+((getCPCost(getDieDoubleRep(skills.get(percSkills[i])[0],skills.get(percSkills[i])[1])))
                        -(getCPCost(getDieDoubleRep(perc[0],perc[1]))));;
            }
        }
        
        //Checks dexterity skill deltas between this and other Character
        for(int i = 0; i < dexSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(dexSkills[i]),dex))
            {   
               deltaCPs=deltaCPs+((getCPCost(getDieDoubleRep(skills.get(dexSkills[i])[0],skills.get(dexSkills[i])[1])))
                        -(getCPCost(getDieDoubleRep(dex[0],dex[1]))));
            }
        }
        
        //Checks strength skill deltas between this and other Character
        for(int i = 0; i < strSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(strSkills[i]),str))
            {   
                deltaCPs=deltaCPs+((getCPCost(getDieDoubleRep(skills.get(strSkills[i])[0],skills.get(strSkills[i])[1])))
                        -(getCPCost(getDieDoubleRep(str[0],str[1]))));
            }
        }
        
        //Checks knowledge skill deltas between this and other Character
        for(int i = 0; i < knowSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(knowSkills[i]),know))
            {   
                deltaCPs=deltaCPs+((getCPCost(getDieDoubleRep(skills.get(knowSkills[i])[0],skills.get(knowSkills[i])[1])))
                        -(getCPCost(getDieDoubleRep(know[0],know[1]))));
            }
        }
        
        //Checks mechanical skill deltas between this and other Character
        for(int i = 0; i < mechSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(mechSkills[i]),mech))
            {   
               deltaCPs=deltaCPs+((getCPCost(getDieDoubleRep(skills.get(mechSkills[i])[0],skills.get(mechSkills[i])[1])))
                        -(getCPCost(getDieDoubleRep(mech[0],mech[1]))));
            }
        }
        
        //Checks technical skill deltas between this and other Character
        for(int i = 0; i < techSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(techSkills[i]),tech))
            {   
                 deltaCPs=deltaCPs+ ((getCPCost(getDieDoubleRep(skills.get(techSkills[i])[0],skills.get(techSkills[i])[1])))
                        -(getCPCost(getDieDoubleRep(tech[0],tech[1]))));
            }
        }
        
        //System.out.println(this.characterName + " character points: "+deltaCPs);
        return deltaCPs;
    }
    
    public String getCharacterName()
    {
        return this.characterName;
    }
    
    public String getFileName()
    {
    	JFileChooser fileChooser = new JFileChooser("C:\\Users\\bradf\\Dropbox\\SWCharGen JSONs\\");
    	int result = fileChooser.showOpenDialog(fileChooser);
    	//file.getAbsolutePath()
    	
    	if (result == JFileChooser.APPROVE_OPTION) 
    	{
    	    File selectedFile = fileChooser.getSelectedFile();
    	    return  selectedFile.getAbsolutePath();
    	}
    	else {
    	return "";
    	}
    }
    
    public String saveFileName()
    {
    	JFileChooser fileChooser = new JFileChooser("C:\\Users\\bradf\\Dropbox\\SWCharGen JSONs\\");
    	int result = fileChooser.showSaveDialog(fileChooser);
    	//file.getAbsolutePath()
    	
    	if (result == JFileChooser.APPROVE_OPTION) 
    	{
    	    File selectedFile = fileChooser.getSelectedFile();
    	    return  selectedFile.getAbsolutePath();
    	}
    	else {
    	return "";
    	}
    }
    
    public void setCPs(int charPoints)
    {
        characterPoints=charPoints;
    }
    
    public void resetCPs()
    {
        characterPoints=0;
    }
    
    public void setSkillValue(String skill, int die, int pip)
    {   
        
        if(skills.containsKey(skill))
        {
            double oldDieValue = getSkill(skill);
            double newDieValue = getDieDoubleRep(die,pip);
            int[] newSkillValue = {die,pip};
            int cpChange = getCPCost(newDieValue)-getCPCost(oldDieValue);
            characterPoints = characterPoints - cpChange;
            skills.replace(skill,newSkillValue);
        }
        
        else
        {
            System.out.println("Pick a proper skill, "+skill+" is incorrect");
        }
    }
    
    public void setStatValue(String statName, int die, int pip)
    {
        double newDieValue = getDieDoubleRep(die,pip);
        double oldDieValue;
        int cpChange;
        String stat = statName.toLowerCase();
        
        if(stat.equals("perception")){
            oldDieValue = getDieDoubleRep(perc[0],perc[1]);
            cpChange = (getCPCost(newDieValue)-getCPCost(oldDieValue))*10;
            characterPoints = characterPoints - cpChange;
            
            perc[0] = die; perc[1] = pip;
        }
        else if(stat.equals("dexterity"))
        {
            oldDieValue = getDieDoubleRep(dex[0],dex[1]);
            cpChange = (getCPCost(newDieValue)-getCPCost(oldDieValue))*10;
            characterPoints = characterPoints - cpChange;
            
            dex[0] = die; dex[1] = pip;
        }
        else if(stat.equals("strength"))
        {
            oldDieValue = getDieDoubleRep(str[0],str[1]);
            cpChange = (getCPCost(newDieValue)-getCPCost(oldDieValue))*10;
            characterPoints = characterPoints - cpChange;
            
            str[0] = die; str[1] = pip;
        }
        else if(stat.equals("knowledge"))
        {
            oldDieValue = getDieDoubleRep(know[0],know[1]);
            cpChange = (getCPCost(newDieValue)-getCPCost(oldDieValue))*10;
            characterPoints = characterPoints - cpChange;
            
            know[0] = die; know[1] = pip;
        }
        else if(stat.equals("mechanical"))
        {
            oldDieValue = getDieDoubleRep(mech[0],mech[1]);
            cpChange = (getCPCost(newDieValue)-getCPCost(oldDieValue))*10;
            characterPoints = characterPoints - cpChange;
            
            mech[0] = die; mech[1] = pip;
        }
        else if(stat.equals("technical"))
        {
            oldDieValue = getDieDoubleRep(tech[0],tech[1]);
            cpChange = (getCPCost(newDieValue)-getCPCost(oldDieValue))*10;
            characterPoints = characterPoints - cpChange;
            
            tech[0] = die; tech[1] = pip;
        }
        else if(stat.equals("alter"))
        {
            oldDieValue = getDieDoubleRep(alter[0],alter[1]);
            if(newDieValue>=1.00 && oldDieValue<1.00) 
            {
            	characterPoints = characterPoints + getCPCost(newDieValue) + 10;
            }
            else if(newDieValue >= 1.00 && oldDieValue >= 1.00){
            	characterPoints = characterPoints - (getCPCost(newDieValue)-getCPCost(oldDieValue));
            }
            
            alter[0] = die; alter[1] = pip;
        }
        else if(stat.equals("control"))
        {
            oldDieValue = getDieDoubleRep(control[0],control[1]);
            if(newDieValue>=1.00 && oldDieValue<1.00) 
            {
            	characterPoints = characterPoints + getCPCost(newDieValue) + 10;
            }
            else if(newDieValue >= 1.00 && oldDieValue >= 1.00){
            	characterPoints = characterPoints - (getCPCost(newDieValue)-getCPCost(oldDieValue));
            }
            control[0] = die; control[1] = pip;
            
        }
        else if(stat.equals("sense"))
        {
            oldDieValue = getDieDoubleRep(sense[0],sense[1]);
            if(newDieValue>=1.00 && oldDieValue<1.00) 
            {
            	characterPoints = characterPoints + getCPCost(newDieValue) + 10;
            }
            else if(newDieValue >= 1.00 && oldDieValue >= 1.00){
            	characterPoints = characterPoints - (getCPCost(newDieValue)-getCPCost(oldDieValue));
            }
            sense[0] = die; sense[1] = pip;
            
        }
        else{
            System.out.println("Try one of the following: Strength, Dexterity, Perception, Knowledge, Technical, Mechanical, Alter, Control, or Sense");
        }
    }
    
    public void setCharacterName(String characterName)
    {
    	this.characterName = characterName;
        }
    
    public int compSkills(Character other)
    {
        int deltaCPs = 0; 
        
        if(!Arrays.equals(dex,other.dex))
        {
                deltaCPs=deltaCPs+10*(getCPCost(getDieDoubleRep(dex[0],dex[1]))
                		-getCPCost(getDieDoubleRep(other.dex[0],other.dex[1])));
        }
        if(!Arrays.equals(str,other.str))
        {
                deltaCPs=deltaCPs+10*(getCPCost(getDieDoubleRep(str[0],str[1]))
                		-getCPCost(getDieDoubleRep(other.str[0],other.str[1])));
        }
        if(!Arrays.equals(perc,other.perc))
        {
                deltaCPs=deltaCPs+10*(getCPCost(getDieDoubleRep(perc[0],perc[1]))
                		-getCPCost(getDieDoubleRep(other.perc[0],other.perc[1])));
        }
        if(!Arrays.equals(know,other.know))
        {
                deltaCPs=deltaCPs+10*(getCPCost(getDieDoubleRep(know[0],know[1]))
                		-getCPCost(getDieDoubleRep(other.know[0],other.know[1])));
        }
        if(!Arrays.equals(mech,other.mech))
        {
        	deltaCPs=deltaCPs+10*(getCPCost(getDieDoubleRep(mech[0],mech[1]))
                		-getCPCost(getDieDoubleRep(other.mech[0],other.mech[1])));
        }
        if(!Arrays.equals(tech,other.tech)){
        	deltaCPs=deltaCPs+10*(getCPCost(getDieDoubleRep(tech[0],tech[1]))
        			-getCPCost(getDieDoubleRep(other.tech[0],other.tech[1])));
        }
        if(!Arrays.equals(alter,other.alter)){
        	int value1 = getCPCost(getDieDoubleRep(alter[0],alter[1]));
        	int value2 = getCPCost(getDieDoubleRep(other.alter[0],other.alter[1]));
        	if(value1<1 && value2>=1) {
        		deltaCPs=deltaCPs+(value1 -10 - value2);
        	}
        	else if(value2<1 && value1>=1) {
        		deltaCPs=deltaCPs+(value1 + 10 - value2);
        	}
        	else {
        		deltaCPs=deltaCPs+(value1  - value2);
        	}
        }
        if(!Arrays.equals(control,other.control)){
        	int value1 = getCPCost(getDieDoubleRep(control[0],control[1]));
        	int value2 = getCPCost(getDieDoubleRep(other.control[0],other.control[1]));
        	if(value1<1 && value2>=1) {
        		deltaCPs=deltaCPs+(value1 -10 - value2);
        	}
        	else if(value2<1 && value1>=1) {
        		deltaCPs=deltaCPs+(value1 + 10 - value2);
        	}
        	else {
        		deltaCPs=deltaCPs+(value1  - value2);
        	}
        }
        if(!Arrays.equals(sense,other.sense)){
        	int value1 = getCPCost(getDieDoubleRep(sense[0],sense[1]));
        	int value2 = getCPCost(getDieDoubleRep(other.sense[0],other.sense[1]));
        	if(value1<1 && value2>=1) {
        		deltaCPs=deltaCPs+(value1 -10 - value2);
        	}
        	else if(value2<1 && value1>=1) {
        		deltaCPs=deltaCPs+(value1 + 10 - value2);
        	}
        	else {
        		deltaCPs=deltaCPs+(value1  - value2);
        	}
        }
                
        //Checks perception skill deltas between this and other Character
        for(int i = 0; i < percSkills.length; i++)
        {   
            if(!Arrays.equals(skills.get(percSkills[i]),perc) || !Arrays.equals(other.skills.get(percSkills[i]),other.perc))
            {   
                deltaCPs = deltaCPs + (((getCPCost(getDieDoubleRep(skills.get(percSkills[i])[0],skills.get(percSkills[i])[1])))
                -(getCPCost(getDieDoubleRep(perc[0],perc[1]))))
                -((getCPCost(getDieDoubleRep(other.skills.get(percSkills[i])[0],other.skills.get(percSkills[i])[1])))
                -(getCPCost(getDieDoubleRep(other.perc[0],other.perc[1])))));
            }
        }
        
        //Checks dexterity skill deltas between this and other Character
        for(int i = 0; i < dexSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(dexSkills[i]),dex) || !Arrays.equals(other.skills.get(dexSkills[i]),other.dex))
            {   
                deltaCPs = deltaCPs+ (((getCPCost(getDieDoubleRep(skills.get(dexSkills[i])[0],skills.get(dexSkills[i])[1])))
                -(getCPCost(getDieDoubleRep(dex[0],dex[1]))))
                -((getCPCost(getDieDoubleRep(other.skills.get(dexSkills[i])[0],other.skills.get(dexSkills[i])[1])))
                -(getCPCost(getDieDoubleRep(other.dex[0],other.dex[1])))));
            }
        }
        
        //Checks strength skill deltas between this and other Character
        for(int i = 0; i < strSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(strSkills[i]),str) || !Arrays.equals(other.skills.get(strSkills[i]),other.str))
            {   
                deltaCPs = deltaCPs + (((getCPCost(getDieDoubleRep(skills.get(strSkills[i])[0],skills.get(strSkills[i])[1])))
                -(getCPCost(getDieDoubleRep(str[0],str[1]))))
                -((getCPCost(getDieDoubleRep(other.skills.get(strSkills[i])[0],other.skills.get(strSkills[i])[1])))
                -(getCPCost(getDieDoubleRep(other.str[0],other.str[1])))));
            }
        }
        
        //Checks knowledge skill deltas between this and other Character
        for(int i = 0; i < knowSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(knowSkills[i]),know) || !Arrays.equals(other.skills.get(knowSkills[i]),other.know))
            {   
                deltaCPs = deltaCPs + (((getCPCost(getDieDoubleRep(skills.get(knowSkills[i])[0],skills.get(knowSkills[i])[1])))
                -(getCPCost(getDieDoubleRep(know[0],know[1]))))
                -((getCPCost(getDieDoubleRep(other.skills.get(knowSkills[i])[0],other.skills.get(knowSkills[i])[1])))
                -(getCPCost(getDieDoubleRep(other.know[0],other.know[1])))));
            }
        }
        
        //Checks mechanical skill deltas between this and other Character
        for(int i = 0; i < mechSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(mechSkills[i]),mech) || !Arrays.equals(other.skills.get(mechSkills[i]),other.mech))
            {   
                deltaCPs = deltaCPs + (((getCPCost(getDieDoubleRep(skills.get(mechSkills[i])[0],skills.get(mechSkills[i])[1])))
                -(getCPCost(getDieDoubleRep(mech[0],mech[1]))))
                -((getCPCost(getDieDoubleRep(other.skills.get(mechSkills[i])[0],other.skills.get(mechSkills[i])[1])))
                -(getCPCost(getDieDoubleRep(other.mech[0],other.mech[1])))));
            }
        }
        
        //Checks technical skill deltas between this and other Character
        for(int i = 0; i < techSkills.length; i++)
        {
            if(!Arrays.equals(skills.get(techSkills[i]),tech) || !Arrays.equals(other.skills.get(techSkills[i]),other.tech))
            {   
                deltaCPs = deltaCPs + (((getCPCost(getDieDoubleRep(skills.get(techSkills[i])[0],skills.get(techSkills[i])[1])))
                -(getCPCost(getDieDoubleRep(tech[0],tech[1]))))
                -((getCPCost(getDieDoubleRep(other.skills.get(techSkills[i])[0],other.skills.get(techSkills[i])[1])))
                -(getCPCost(getDieDoubleRep(other.tech[0],other.tech[1])))));
            }
        }
        
        System.out.println("Difference in CPs between "+other.getCharacterName()+" and "+this.characterName+": "+deltaCPs);
        
        return deltaCPs;
    }

    public void writeToFile() 
    {
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	JsonObject obj = new JsonObject();
        obj.addProperty("Name", this.getCharacterName().toString());
        System.out.println(this.characterName + " " +obj.get("Name").toString());
        obj.addProperty("CPs", getCharacterPoints());

        JsonArray level_1 = new JsonArray();
        JsonObject level_2_Perc = new JsonObject();
        JsonObject level_3_Perc_Skills= new JsonObject();
        JsonObject level_2_Dex = new JsonObject();
        JsonObject level_3_Dex_Skills= new JsonObject();
        JsonObject level_2_Str = new JsonObject();
        JsonObject level_3_Str_Skills= new JsonObject();
        JsonObject level_2_Know = new JsonObject();
        JsonObject level_3_Know_Skills= new JsonObject();
        JsonObject level_2_Tech = new JsonObject();
        JsonObject level_3_Tech_Skills= new JsonObject();
        JsonObject level_2_Mech = new JsonObject();
        JsonObject level_3_Mech_Skills= new JsonObject();
        JsonObject level_2_Alter = new JsonObject();
        JsonObject level_2_Control = new JsonObject();
        JsonObject level_2_Sense = new JsonObject();
        
        level_2_Perc.addProperty("Perception", getDieStringRep("Perception"));
        for(int i = 0; i < percSkills.length; i++) {
        	level_3_Perc_Skills.addProperty(percSkills[i], getDieStringRep(percSkills[i]));
        }
        level_2_Perc.add("Perception Skills", level_3_Perc_Skills);
        
        level_2_Dex.addProperty("Dexterity", getDieStringRep("Dexterity"));
        for(int i = 0; i < dexSkills.length; i++) {
        	level_3_Dex_Skills.addProperty(dexSkills[i], getDieStringRep(dexSkills[i]));
        }
        level_2_Dex.add("Dexterity Skills", level_3_Dex_Skills);
        
        level_2_Str.addProperty("Strength", getDieStringRep("Strength"));
        for(int i = 0; i < strSkills.length; i++) {
        	level_3_Str_Skills.addProperty(strSkills[i], getDieStringRep(strSkills[i]));
        }
        level_2_Str.add("Strength Skills", level_3_Str_Skills);
        
        level_2_Know.addProperty("Knowledge", getDieStringRep("Knowledge"));
        for(int i = 0; i < knowSkills.length; i++) {
        	level_3_Know_Skills.addProperty(knowSkills[i], getDieStringRep(knowSkills[i]));
        }
        level_2_Know.add("Knowledge Skills", level_3_Know_Skills);
        
        level_2_Tech.addProperty("Technical", getDieStringRep("Technical"));
        for(int i = 0; i < techSkills.length; i++) {
        	level_3_Tech_Skills.addProperty(techSkills[i], getDieStringRep(techSkills[i]));
        }
        level_2_Tech.add("Technical Skills", level_3_Tech_Skills);
        
        level_2_Mech.addProperty("Mechanical", getDieStringRep("Mechanical"));
        for(int i = 0; i < mechSkills.length; i++) {
        	level_3_Mech_Skills.addProperty(mechSkills[i], getDieStringRep(mechSkills[i]));
        }
        level_2_Mech.add("Mechanical Skills", level_3_Mech_Skills);
        level_2_Alter.addProperty("Alter", getDieStringRep("Alter"));
        level_2_Control.addProperty("Control", getDieStringRep("Control"));
        level_2_Sense.addProperty("Sense", getDieStringRep("Sense"));
        
       
        level_1.add(level_2_Perc);
        level_1.add(level_2_Dex);
        level_1.add(level_2_Str);
        level_1.add(level_2_Know);
        level_1.add(level_2_Tech);
        level_1.add(level_2_Mech);
        level_1.add(level_2_Alter);
        level_1.add(level_2_Control);
        level_1.add(level_2_Sense);
        obj.add("Stats",  level_1);
        String saveFileName = saveFileName();
        System.out.println(saveFileName);
        try (FileWriter file = new FileWriter(saveFileName)) {  //"C:\\Users\\Bradf\\Dropbox\\"+getCharacterName()+".json"
        	String json = gson.toJson(obj);
        	file.write(json);
        	file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(String fileName) 
    {
    	JsonParser charcterParser = new JsonParser();
    	JsonObject jsonObj = new JsonObject();
    	try {
    		Object obj = charcterParser.parse(new FileReader(fileName));
    		jsonObj = (JsonObject) obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	setCharacterName(jsonObj.get("Name").toString());
//    	System.out.println(jsonObj.get("Name").toString().getClass());
    	JsonArray array1 = jsonObj.get("Stats").getAsJsonArray();
    	JsonObject array2 = new JsonObject();
    	JsonObject array3 = new JsonObject();
    	
    	for(int i = 0; i < 6; i++) 
    	{
    		array2 = (JsonObject) array1.get(i);
    		array3 = (JsonObject) array2.get(statList.get(i)+" Skills");
    		int statDieValue = getDieDoubleRepFromString(array2.get(statList.get(i)).toString())[0];
			int statPipValue = getDieDoubleRepFromString(array2.get(statList.get(i)).toString())[1];
    		setStatValue(statList.get(i),statDieValue,statPipValue);
    		
    		if(array3.has(percSkills[i])) {
    			
	    		for(int j = 0; j <percSkills.length; j++)
	    		{
	    			int dieValue = getDieDoubleRepFromString(array3.get(percSkills[j]).toString())[0];
	    			int pipValue = getDieDoubleRepFromString(array3.get(percSkills[j]).toString())[1];
	    			setSkillValue(percSkills[j],dieValue,pipValue);
	    		}
    		}
    		else if(array3.has(dexSkills[i])) {
	    		for(int j = 0; j <dexSkills.length; j++)
	    		{
	    			int dieValue = getDieDoubleRepFromString(array3.get(dexSkills[j]).toString())[0];
	    			int pipValue = getDieDoubleRepFromString(array3.get(dexSkills[j]).toString())[1];
	    			setSkillValue(dexSkills[j],dieValue,pipValue);
	    		}
    		}
    		else if(array3.has(strSkills[i])) {
	    		for(int j = 0; j <strSkills.length; j++)
	    		{
	    			int dieValue = getDieDoubleRepFromString(array3.get(strSkills[j]).toString())[0];
	    			int pipValue = getDieDoubleRepFromString(array3.get(strSkills[j]).toString())[1];
	    			setSkillValue(strSkills[j],dieValue,pipValue);
	    		}
    		}
    		else if(array3.has(knowSkills[i])) {
	    		for(int j = 0; j <knowSkills.length; j++)
	    		{
	    			int dieValue = getDieDoubleRepFromString(array3.get(knowSkills[j]).toString())[0];
	    			int pipValue = getDieDoubleRepFromString(array3.get(knowSkills[j]).toString())[1];
	    			setSkillValue(knowSkills[j],dieValue,pipValue);
	    		}
    		}
    		else if(array3.has(techSkills[i])) {
	    		for(int j = 0; j <techSkills.length; j++)
	    		{
	    			int dieValue = getDieDoubleRepFromString(array3.get(techSkills[j]).toString())[0];
	    			int pipValue = getDieDoubleRepFromString(array3.get(techSkills[j]).toString())[1];
	    			setSkillValue(techSkills[j],dieValue,pipValue);
	    		}
    		}
    		else if(array3.has(mechSkills[i])) {
	    		for(int j = 0; j <mechSkills.length; j++)
	    		{
	    			int dieValue = getDieDoubleRepFromString(array3.get(mechSkills[j]).toString())[0];
	    			int pipValue = getDieDoubleRepFromString(array3.get(mechSkills[j]).toString())[1];
	    			setSkillValue(mechSkills[j],dieValue,pipValue);
	    		}
    		}
    	}
    	for(int i = 6; i < 9; i++) 
    	{
    		array2 = (JsonObject) array1.get(i);
    		int statDieValue = getDieDoubleRepFromString(array2.get(statList.get(i)).toString())[0];
			int statPipValue = getDieDoubleRepFromString(array2.get(statList.get(i)).toString())[1];
			setStatValue(statList.get(i),statDieValue,statPipValue);
    	}
    	getSkillsCPs();
    	setCPs(Integer.parseInt(jsonObj.get("CPs").toString()));
    }
    
    public static void main(String[] args) {
        //input stat order: Character(dex, str, perc, know, mech, tech)
    
        Character character1 = new Character("character1",1,0,1,0,1,0,1,0,1,0,1,0);
//        Character character2 = new Character("character2",1,0,1,0,1,0,1,0,1,0,1,0);
//        Character character3 = new Character("character3",1,0,1,0,1,0,1,0,1,0,1,0);
//        Character character4 = new Character("character4",1,0,1,0,1,0,1,0,1,0,1,0);
        
//        Character characterBlank = new Character("blank character",1,0,1,0,1,0,1,0,1,0,1,0);
//        characterBlank.writeToFile(); 
        
        System.out.println(character1.getCharacterName() + " has spent " + character1.skillsCharacterPoints + " CPs for skills ("+ character1.getCombatSkillsCPs() + " on combat skills, " + (character1.skillsCharacterPoints-character1.getCombatSkillsCPs()) + " on non-combat skills)");
//        System.out.println(character2.getCharacterName() + " has spent " + character2.skillsCharacterPoints + " CPs for skills ("+ character2.getCombatSkillsCPs() + " on combat skills, " + (character2.skillsCharacterPoints-character2.getCombatSkillsCPs()) + " on non-combat skills)");
//        character2.compSkills(character1);
//        character1.setStatValue("control", 2, 0);
//        System.out.println(character1.getCharacterName());
        character1.writeToFile();
        
//        System.out.println(character3.getCharacterName() + " has spent " + character3.skillsCharacterPoints + " CPs for skills ("+ character3.getCombatSkillsCPs() + " on combat skills, " + (character3.skillsCharacterPoints-character3.getCombatSkillsCPs()) + " on non-combat skills)");
//        character3.compSkills(character2);
//        character3.compSkills(character1);
//        System.out.println(character4.getCharacterName() + " has spent " + character4.skillsCharacterPoints + " CPs for skills ("+ character4.getCombatSkillsCPs() + " on combat skills, " + (character4.skillsCharacterPoints-character4.getCombatSkillsCPs()) + " on non-combat skills)");
//        character4.compSkills(character3);
//        character4.compSkills(character1);
        
        //TODO: specializations, initial stat buy, initial skill buy, comment
       
    }
}
