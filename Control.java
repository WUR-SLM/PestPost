/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pestpost;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import org.ini4j.Ini;

/**
 *
 * @author wesse016
 */
public class Control extends Thread{
  
  private Thread controlThread;
  private String threadName;
  
  public String logLine;
  private String lineSeparator;
  
   
  
  private File iniFile;
   
  public Boolean singleRun;
  
  private String runCore;
  private String segmentFile;
  private String outflowFile;
  public Control(){
    singleRun = false;
    threadName = "ControlThread";
    lineSeparator = System.getProperty("line.separator");
    String iniFileName = System.getProperty("user.dir");
    iniFileName = iniFileName.concat("/PestPost.ini");           
    iniFile = new File(iniFileName);
    if (!iniFile.exists()){
      showMessage("??? Error: file " + iniFileName + " does not exist!");
    }
  }
  
   private List _listeners = new ArrayList();
    public synchronized void addEventListener(AddToLogEventClassListener listener)	{
        boolean //<editor-fold defaultstate="collapsed" desc="comment">
                add
                //</editor-fold>
 = _listeners.add(listener);
    }
    public synchronized void removeEventListener(AddToLogEventClassListener listener)	{
      _listeners.remove(listener);
    }

    // call this method whenever you want to notify
    //the event listeners of the particular event
    private synchronized void fireEvent()	{
      AddToLogEventClass event = new AddToLogEventClass(this);
      Iterator i = _listeners.iterator();
      while(i.hasNext())	{
        ((AddToLogEventClassListener)i.next()).handleAddToLogEventClassEvent(event);
      }
    }

  private void showMessage(String aString){
    logLine = aString.concat(lineSeparator);
    fireEvent();
  }
  
  public void readLisemFileNames(JTextField aDEM,
                                 JTextField aKSat,
                                 JTextField aThetaSat,
                                 JTextField aThetaIni,
                                 JTextField aInfil,
                                 JTextField aDirections,
                                 JTextField aPesticide,
                                 JTextField aSurfaceWater,
                                 JTextField aVelocity,
                                 JTextField aRunoff,
                                 JTextField aSedimentConcentration,
                                 JTextField aPrecipitation,
                                 JTextField aPrecipitationData,
                                 JTextField aTimestep){
    Ini ini = new Ini();
    
    try {
      ini.load(new FileReader(iniFile));
      String myString = ini.fetch("Lisem", "DEM");
      aDEM.setText(myString);
      myString = ini.fetch("Lisem", "KSat");
      aKSat.setText(myString);
      myString = ini.fetch("Lisem", "ThetaSat");
      aThetaSat.setText(myString);
      myString = ini.fetch("Lisem", "ThetaIni");
      aThetaIni.setText(myString);
      myString = ini.fetch("Lisem", "Infil");
      aInfil.setText(myString);
      myString = ini.fetch("Lisem", "Velocity");
      aVelocity.setText(myString);
      myString = ini.fetch("Lisem", "Runoff");
      aRunoff.setText(myString);
      myString = ini.fetch("Lisem", "SedimentConcentration");
      aSedimentConcentration.setText(myString);
      myString = ini.fetch("Lisem", "Directions");
      aDirections.setText(myString);
      myString = ini.fetch("Lisem", "Pesticide");
      aPesticide.setText(myString);
      myString = ini.fetch("Lisem", "SurfaceWaterHeight");
      aSurfaceWater.setText(myString);
      myString = ini.fetch("Lisem", "Precipitation");
      aPrecipitation.setText(myString);
      myString = ini.fetch("Lisem", "PrecipitationData");
      aPrecipitationData.setText(myString);
      myString = ini.fetch("Lisem", "Timestep");
      aTimestep.setText(myString);
    } catch (Exception e) {
      showMessage(e.getMessage());
    }
  }
  public void readSingleRun(JTextField aTopConc,
                                JTextField aVelocity,
                                JTextField aFile){
    Ini ini = new Ini();
    
    try {
      ini.load(new FileReader(iniFile));
      String myString = ini.fetch("SingleRun", "cTop");
      aTopConc.setText(myString);
      myString = ini.fetch("SingleRun", "Velocity");
      aVelocity.setText(myString);
      myString = ini.fetch("SingleRun", "OutputFile");
      aFile.setText(myString);
    } catch (Exception e) {
      showMessage(e.getMessage());
    }
  }
  
  public void readOutputFileNames(JTextField aOutputDir, JTextField aThetaFC, JTextField aOrder, JTextField aLosses, JTextField aSegments, 
          JCheckBox aRequired1, JCheckBox aRequired2, JCheckBox aRequired3, JCheckBox aRequired4, JCheckBox aRequired5,
          JCheckBox aRequired6, JCheckBox aRequired7, JCheckBox aRequired8, JCheckBox aRequired9, JCheckBox aRequired10,
          JTextField aDepth1, JTextField aDepth2, JTextField aDepth3, JTextField aDepth4, JTextField aDepth5, 
          JTextField aDepth6, JTextField aDepth7, JTextField aDepth8, JTextField aDepth9, JTextField aDepth10,
          JCheckBox aNewFiles){
    Ini ini = new Ini();
    
    try {
      ini.load(new FileReader(iniFile));
      String myString = ini.fetch("Output","Dir");
      aOutputDir.setText(myString);
      myString = ini.fetch("Output", "ThetaFC");
      aThetaFC.setText(myString);
      myString = ini.fetch("Output", "Order");
      aOrder.setText(myString);
      myString = ini.fetch("Output", "Losses");
      aLosses.setText(myString);
      myString = ini.fetch("Output", "Segments");
      aSegments.setText(myString);
      
      aDepth1.setText(ini.fetch("Output","Depth1"));
      aDepth2.setText(ini.fetch("Output","Depth2"));
      aDepth3.setText(ini.fetch("Output","Depth3"));
      aDepth4.setText(ini.fetch("Output","Depth4"));
      aDepth5.setText(ini.fetch("Output","Depth5"));
      aDepth6.setText(ini.fetch("Output","Depth6"));
      aDepth7.setText(ini.fetch("Output","Depth7"));
      aDepth8.setText(ini.fetch("Output","Depth8"));
      aDepth9.setText(ini.fetch("Output","Depth9"));
      aDepth10.setText(ini.fetch("Output","Depth10"));
      
      aRequired10.setSelected(ini.fetch("Output", "Depth10Required").trim().equals("1"));
      aRequired9.setSelected(ini.fetch("Output", "Depth9Required").trim().equals("1"));
      aRequired8.setSelected(ini.fetch("Output", "Depth8Required").trim().equals("1"));
      aRequired7.setSelected(ini.fetch("Output", "Depth7Required").trim().equals("1"));
      aRequired6.setSelected(ini.fetch("Output", "Depth6Required").trim().equals("1"));
      aRequired5.setSelected(ini.fetch("Output", "Depth5Required").trim().equals("1"));
      aRequired4.setSelected(ini.fetch("Output", "Depth4Required").trim().equals("1"));
      aRequired3.setSelected(ini.fetch("Output", "Depth3Required").trim().equals("1"));
      aRequired2.setSelected(ini.fetch("Output", "Depth2Required").trim().equals("1"));
      aRequired1.setSelected(ini.fetch("Output", "Depth1Required").trim().equals("1"));
      
     aNewFiles.setSelected(ini.fetch("Output", "NewFiles").trim().equals("1"));      
      
     runCore = ini.fetch("Run", "Core");
     
    } catch (Exception e) {
      showMessage(e.getMessage());
    }
  }
 
   public void readPesticideData(JTextField aSteps,
                                 JTextField aProfileDepth,
                                 JTextField aInitialC,
                                 JTextField aAppliedC,
                                 JTextField aRetardation,
                                 JTextField aDispersion,
                                 JTextField aDegradation,
                                 JTextField aHalfTime,
                                 JTextField aMaxCw,
                                 JTextField aMaxCs,
                                 JTextField aInfiltrationFactor,
                                 JTextField aInitialCellContent){
    Ini ini = new Ini();
    
    try {
      ini.load(new FileReader(iniFile));
      String myString = ini.fetch("Pesticide", "TimeSteps");
      aSteps.setText(myString);
      myString = ini.fetch("Pesticide", "Depth");
      aProfileDepth.setText(myString);
      myString = ini.fetch("Pesticide", "InitialC");
      aInitialC.setText(myString);
      myString = ini.fetch("Pesticide", "AppliedC");
      aAppliedC.setText(myString);
      myString = ini.fetch("Pesticide", "Retardation");
      aRetardation.setText(myString);
      myString = ini.fetch("Pesticide", "Dispersion");
      aDispersion.setText(myString);
      myString = ini.fetch("Pesticide", "Degradation");
      aDegradation.setText(myString);
      myString = ini.fetch("Pesticide", "HalfTime");
      aHalfTime.setText(myString);
      myString = ini.fetch("Pesticide", "MaxCw");
      aMaxCw.setText(myString);
      myString = ini.fetch("Pesticide", "MaxCs");
      aMaxCs.setText(myString);
      myString = ini.fetch("Pesticide", "fInfiltration");
      aInfiltrationFactor.setText(myString);
      myString = ini.fetch("Pesticide", "InitialContent");
      aInitialCellContent.setText(myString);
    } catch (Exception e) {
      showMessage(e.getMessage());
    }
  }
 
   public void readCoreData(JTextField aCore,
                             JCheckBox aSurfaceOnly){
    Ini ini = new Ini();
    
    try {
      ini.load(new FileReader(iniFile));
      String myString = ini.fetch("Run", "Core");
      aCore.setText(myString);
      myString = ini.fetch("Run", "SurfaceOnly");
      aSurfaceOnly.setSelected(myString.equals("1"));
    } catch (Exception e) {
      showMessage(e.getMessage());
    }
  }
 
  public void storeLisemFileNames(JTextField aDEM,
                                JTextField aKSat,
                                JTextField aThetaSat,
                                JTextField aThetaIni,
                                JTextField aInfil,
                                JTextField aDirections,
                                JTextField aPesticide,
                                JTextField aSurfaceWater,
                                JTextField aVelocity,
                                JTextField aRunoff,
                                JTextField aSedimentConcentration,
                                JTextField aPrecipitation,
                                JTextField aPrecipitationData,
                                JTextField aTimestep){
    Ini ini = new Ini();
    
    try {
      ini.load(new FileReader(iniFile));
      ini.put("Lisem", "DEM", aDEM.getText());
      ini.put("Lisem", "KSat", aKSat.getText());
      ini.put("Lisem", "ThetaSat", aThetaSat.getText());
      ini.put("Lisem", "ThetaIni", aThetaIni.getText());
      ini.put("Lisem", "Infil", aInfil.getText());
      ini.put("Lisem", "Directions", aDirections.getText());
      ini.put("Lisem", "Pesticide", aPesticide.getText());
      ini.put("Lisem", "SurfaceWaterHeight", aSurfaceWater.getText());
      ini.put("Lisem", "Velocity", aVelocity.getText());
      ini.put("Lisem", "Runoff", aRunoff.getText());
      ini.put("Lisem", "SedimentConcentration", aSedimentConcentration.getText());
      ini.put("Lisem", "Precipitation", aPrecipitation.getText());
      ini.put("Lisem", "PrecipitationData", aPrecipitationData.getText());
      ini.put("Lisem", "Timestep", aTimestep.getText());
      ini.store(iniFile);
    } catch (Exception e) {
      showMessage(e.getMessage());
    }
  }
  
  public void storeSingleRun(JTextField aTopConc,
                                JTextField aVelocity,
                                JTextField aFile){
    Ini ini = new Ini();
    
    try {
      ini.load(new FileReader(iniFile));
      ini.put("SingleRun", "cTop", aTopConc.getText());
      ini.put("SingleRun", "Velocity", aVelocity.getText());
      ini.put("SingleRun", "OutputFile", aFile.getText());
      ini.store(iniFile);
    } catch (Exception e) {
      showMessage(e.getMessage());
    }
  }

  public void storeCoreData(JTextField aCore,
                            JCheckBox aSurfaceOnly){
    Ini ini = new Ini();
    
    try {
      ini.load(new FileReader(iniFile));
      ini.put("Run", "Core", aCore.getText());
      if (aSurfaceOnly.isSelected()){
        ini.put("Run", "SurfaceOnly", "1");
      }
      else
      {
        ini.put("Run", "SurfaceOnly", "0");
      }
      ini.store(iniFile);
    } catch (Exception e) {
      showMessage(e.getMessage());
    }
  }

     public void storePesticideData(JTextField aSteps,
                                JTextField aProfileDepth,
                                JTextField aInitialC,
                                JTextField aAppliedC,
                                JTextField aRetardation,
                                JTextField aDispersion,
                                JTextField aDegradation,
                                JTextField aHalfTime,
                                JTextField aMaxCw,
                                JTextField aMaxCs,
                                JTextField aInfiltrationFactor,
                                JTextField aInitialContent){
    Ini ini = new Ini();
    
    try {
      ini.load(new FileReader(iniFile));
      ini.put("Pesticide", "TimeSteps", aSteps.getText());
      ini.put("Pesticide", "Depth", aProfileDepth.getText());
      ini.put("Pesticide", "InitialC", aInitialC.getText());
      ini.put("Pesticide", "AppliedC", aAppliedC.getText());
      ini.put("Pesticide", "Retardation", aRetardation.getText());
      ini.put("Pesticide", "Dispersion", aDispersion.getText());
      ini.put("Pesticide", "Degradation", aDegradation.getText());
      ini.put("Pesticide", "HalfTime", aHalfTime.getText());
      ini.put("Pesticide", "MaxCw", aMaxCw.getText());
      ini.put("Pesticide", "MaxCs", aMaxCs.getText());
      ini.put("Pesticide", "fInfiltration", aInfiltrationFactor.getText());
      ini.put("Pesticide", "InitialContent", aInitialContent.getText());
      ini.store(iniFile);
    } catch (Exception e) {
      showMessage(e.getMessage());
    }
  }

  public void storeOutputFilesAndDepths(JTextField aOutputDir, JTextField aThetaFC, JTextField aOrder, JTextField aLosses, JTextField aSegments,
          JCheckBox aRequired1, JCheckBox aRequired2, JCheckBox aRequired3, JCheckBox aRequired4, JCheckBox aRequired5,
          JCheckBox aRequired6, JCheckBox aRequired7, JCheckBox aRequired8, JCheckBox aRequired9, JCheckBox aRequired10,
          JTextField aDepth1, JTextField aDepth2, JTextField aDepth3, JTextField aDepth4, JTextField aDepth5, 
          JTextField aDepth6, JTextField aDepth7, JTextField aDepth8, JTextField aDepth9, JTextField aDepth10,
          JCheckBox aNewFiles){
    Ini ini = new Ini();
    
    try {
      
      
      ini.load(new FileReader(iniFile));
      if (aRequired1.isSelected()){
        ini.put("Output", "Depth1Required", "1");
      }
      else
      {
        ini.put("Output", "Depth1Required", "0");
      }
      if (aRequired2.isSelected()){
        ini.put("Output", "Depth2Required", "1");
      }
      else
      {
        ini.put("Output", "Depth2Required", "0");
      }
      if (aRequired3.isSelected()){
        ini.put("Output", "Depth3Required", "1");
      }
      else
      {
        ini.put("Output", "Depth3Required", "0");
      }
      if (aRequired4.isSelected()){
        ini.put("Output", "Depth4Required", "1");
      }
      else
      {
        ini.put("Output", "Depth4Required", "0");
      }
      if (aRequired5.isSelected()){
        ini.put("Output", "Depth5Required", "1");
      }
      else
      {
        ini.put("Output", "Depth5Required", "0");
      }
      if (aRequired6.isSelected()){
        ini.put("Output", "Depth6Required", "1");
      }
      else
      {
        ini.put("Output", "Depth6Required", "0");
      }
      if (aRequired7.isSelected()){
        ini.put("Output", "Depth7Required", "1");
      }
      else
      {
        ini.put("Output", "Depth7Required", "0");
      }
      if (aRequired8.isSelected()){
        ini.put("Output", "Depth8Required", "1");
      }
      else
      {
        ini.put("Output", "Depth8Required", "0");
      }
      if (aRequired9.isSelected()){
        ini.put("Output", "Depth9Required", "1");
      }
      else
      {
        ini.put("Output", "Depth9Required", "0");
      }
      if (aRequired10.isSelected()){
        ini.put("Output", "Depth10Required", "1");
      }
      else
      {
        ini.put("Output", "Depth10Required", "0");
      }
      ini.put("Output", "Depth1", aDepth1.getText());
      ini.put("Output", "Depth2", aDepth2.getText());
      ini.put("Output", "Depth3", aDepth3.getText());
      ini.put("Output", "Depth4", aDepth4.getText());
      ini.put("Output", "Depth5", aDepth5.getText());
      ini.put("Output", "Depth6", aDepth6.getText());
      ini.put("Output", "Depth7", aDepth7.getText());
      ini.put("Output", "Depth8", aDepth8.getText());
      ini.put("Output", "Depth9", aDepth9.getText());
      ini.put("Output", "Depth10", aDepth10.getText());
      ini.put("Output", "Dir", aOutputDir.getText());
      ini.put("Output", "ThetaFC", aThetaFC.getText());
      ini.put("Output", "Order", aOrder.getText());
      ini.put("Output", "Losses", aLosses.getText());
      ini.put("Output", "Order", aSegments.getText());
      segmentFile = aSegments.getText();
      outflowFile = aLosses.getText();
      if (aNewFiles.isSelected()){
        ini.put("Output", "NewFiles", "1");
      }
      else
      {
        ini.put("Output", "NewFiles", "0");
      }
      ini.store(iniFile);
    } catch (Exception e) {
      showMessage(e.getMessage());
    }
  }

      
    private void storeFile(String aFile, String aContent, Boolean aAppend){
      try{
        FileWriter fw  = new FileWriter(aFile, aAppend);
        fw.write(aContent);
        fw.close();        
      }
      catch (Exception ex) {
        showMessage(ex.getMessage());
      }
    }

  public ArrayList<SegmentData> readSegmentContents(){
    ArrayList<SegmentData> myData = new ArrayList<SegmentData>();
    BufferedReader input = null;
    int n = 0;

    try{ 
      try {
        FileReader fr = new FileReader(segmentFile);
        input = new BufferedReader(fr);
        String line = input.readLine();
        
        while (line != null) {
          n++;
          if (n > 1) {
            String[] parts = line.split(",");
            try{
              SegmentData d = new SegmentData();
              d.seconds = Double.parseDouble(parts[1].trim());
              for (int i=2; i<parts.length; i++){
                d.content.add(Double.parseDouble(parts[i].trim()));
              }
              myData.add(d);
            }
            catch(Exception e)
            {
              System.out.println(e.getMessage());
            }
          }
          line = input.readLine();
        }
      }
      catch(Exception e) {
        System.out.println(e.getMessage());
      }
      }
      finally
      {
      try {
        input.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    return myData;
  }   

  public ArrayList<OutFlow> readOutflow(String aFile){
    ArrayList<OutFlow> myData = new ArrayList<OutFlow>();
    BufferedReader input = null;
    int n = 0;

    try{ 
      try {
        FileReader fr = new FileReader(aFile);
        input = new BufferedReader(fr);
        String line = input.readLine();
        
        while (line != null) {
          n++;
          if (n > 1) {
            String[] parts = line.split(",");
            try{
              OutFlow o = new OutFlow();
              o.t = Double.parseDouble(parts[0].trim());
              o.water = Double.parseDouble(parts[3].trim());
              o.solid = Double.parseDouble(parts[4].trim());
              myData.add(o);
            }
            catch(Exception e)
            {
              System.out.println(e.getMessage());
            }
          }
          line = input.readLine();
        }
      }
      catch(Exception e) {
        System.out.println(e.getMessage());
      }
      }
      finally
      {
      try {
        input.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    return myData;
  }   

  private void showResults(){
    ArrayList<SegmentData> myDataList = readSegmentContents();
    SegmentData myData = myDataList.get(myDataList.size()-1);
    String myString = String.valueOf(myData.seconds);
    for (Double c : myData.content){
      myString = myString.concat("  ").concat(c.toString());
    }
    myString.concat(lineSeparator);
    showMessage(lineSeparator + "Segments: " + lineSeparator + myString);
  
    ArrayList<OutFlow> outFlow = readOutflow(outflowFile);
    OutFlow myOutflow = outFlow.get(outFlow.size()-1);
    myString = lineSeparator + " Outflow:" + lineSeparator;
    myString = myString + String.valueOf(myOutflow.t) + "  " + String.valueOf(myOutflow.water) + "  " + String.valueOf(myOutflow.solid) + lineSeparator;
    showMessage(myString);
  }
  
  public void run(){
    try{
      showMessage("Start:  " + GregorianCalendar.getInstance().getTime().toString() + lineSeparator);
      String commandFile = System.getProperty("user.dir");
      commandFile = commandFile.concat("/runCore.sh");           
      File myFile = new File(commandFile);
      if (myFile.exists()){
        myFile.delete();
      }
   //   String myCommand = "java -jar -Xms3000m -Xmx3000m " + runPestPostCore + " " + iniFile.getAbsolutePath() + lineSeparator;
      String myCommand = "#!/bin/bash" + lineSeparator + "java -jar -Xms3000m -Xmx3000m " + runCore + " " + iniFile.getAbsolutePath() + lineSeparator;
      storeFile(commandFile, myCommand, false);
      
      myCommand  = "chmod u=rwx,g=rx,o=r " + runCore;
      Process p = Runtime.getRuntime().exec(new String[]{"bash","-c",myCommand});
      myCommand  = "chmod u=rwx,g=rx,o=r " + commandFile;
      Process p2 = Runtime.getRuntime().exec(new String[]{"bash","-c",myCommand});
      
      Runner myRun = new Runner(commandFile, 3600); 
      if (myRun.ok){
         showResults();
      }
      else
      {
        showMessage("Run not finished OK");
      }
    }
    catch (Exception e)
    {
      
      showMessage("????ERROR: " + e.getMessage());
    }
    showMessage("Finish: " + GregorianCalendar.getInstance().getTime().toString() + lineSeparator);
        
  }
  
  public void start ()
   {
//      showMessage("Starting " +  threadName );
      if (controlThread != null)
      {
        controlThread = null;
      }
      controlThread = new Thread (this, threadName);
      controlThread.start();
   }

}

