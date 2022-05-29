import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.*;


public class BeatBox {

    JPanel mainPanel;
    ArrayList<JCheckBox> checkboxList;
    Sequencer sequencer;
    Sequence sequence;
    Track track;
    JFrame theFrame;
    Vector<String> listdata ;
    JList l; 
    String[] instrumentNames  = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acostic Snare", "Crash Cymbal",
                                  "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", 
                                   "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
    int [] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
    public static void main(String[] args) {
        new BeatBox().buildGUI();
       
    }

    private void buildGUI() {
        theFrame = new JFrame("Better BeatBox");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        checkboxList  = new ArrayList<JCheckBox>(); 
        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        
        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);
        
        JButton stop = new JButton("Stop");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);
        
        JButton upTempo = new JButton("Tempo Up");
        upTempo.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);
        
        JButton downTempo = new JButton("Tempo Down");
        downTempo.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTempo);
        
        JButton save = new JButton("Save the Format");
        save.addActionListener(new SaveListener());
        buttonBox.add(save);
        
        JButton open = new JButton("Open Format");
        open.addActionListener(new OpenListener());
        buttonBox.add(open);
        
        JButton clear = new JButton("Clear");
        clear.addActionListener(new ClearListener());
        buttonBox.add(clear);
        JSeparator s = new JSeparator();
        
        buttonBox.add(s);
        
        listdata = new Vector<String>();
         l = new JList();
        l.addListSelectionListener(new ListListener());
        l.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sp = new JScrollPane(l);
        buttonBox.add(l);
        l.setListData(listdata);
        setList();

        
        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for(int i = 0;i<16;i++){
            nameBox.add(new Label(instrumentNames[i]));
        }
        
        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);
        
        theFrame.getContentPane().add(background);
        
        GridLayout grid = new GridLayout(16,16);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);
        
        for(int i = 0;i<256;i++){
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkboxList.add(c);
            mainPanel.add(c);
        }
        
        setUpMidi();
        
        theFrame.setBounds(50,50,300,300);
        theFrame.pack();
        theFrame.setVisible(true);
        
        
    }

    public void setUpMidi() {
        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setList() {
        listdata.add(0,"Grand (256)");
        listdata.add(1, "1 Line");
        listdata.add(2, "2 Line");
        listdata.add(3,"3 Line");
        listdata.add(4,"4 Line");
        listdata.add(5,"5 Line");
        listdata.add(6,"6 Line");
        listdata.add(7,"7 Line");
        listdata.add(8,"8 Line");
        listdata.add(9,"9 Line");
        listdata.add(10,"10 Line");
        listdata.add(11,"11 Line");
        listdata.add(12,"12 Line");
        listdata.add(13,"13 Line");
        listdata.add(14,"14 Line");
        listdata.add(15,"15 Line");
        listdata.add(16,"16 Line");
        
       
    }

   public class ListListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
                      
            if(!e.getValueIsAdjusting()){
                int index =  l.getSelectedIndex();
                
                switch (index) {
                    case 0:
                        for(int i = 0;i<256;i++){
                            JCheckBox check = (JCheckBox) checkboxList.get(i);
                            check.setSelected(true);
                        }   break;
                    case 1:
                        for(int i = 0;i<16;i++){
                            JCheckBox check = (JCheckBox) checkboxList.get(i);
                            check.setSelected(true);
                        }   break;
                    case 2:
                        for(int i = 16;i<32;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    case 3:
                        for(int i = 32;i<48;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    case 4:
                        for(int i = 48;i<64;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    case 5:
                        for(int i = 64;i<80;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    case 6:
                        for(int i = 80;i<96;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    case 7:
                        for(int i = 96;i<112;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    case 8:
                        for(int i = 112;i<128;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    case 9:
                        for(int i = 128;i<144;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    case 10:
                        for(int i = 144;i<160;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    case 11:
                        for(int i = 160;i<176;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    case 12:
                        for(int i = 176;i<192;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    case 13:
                        for(int i = 192;i<208;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    case 14:
                        for(int i = 208;i<224;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    case 15:
                        for(int i = 224;i<240;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    case 16:
                        for(int i = 240;i<256;i++){
                            JCheckBox c = (JCheckBox) checkboxList.get(i);
                            c.setSelected(true);
                        }
                        break;
                    default:
                        break;
                }
                
                
                
                
                
            }
            
        }
    }

    public class ClearListener implements ActionListener {

        public void actionPerformed(ActionEvent a) {
            for(int i = 0; i<256;i++){
                JCheckBox c = (JCheckBox) checkboxList.get(i);
                c.setSelected(false);
                sequencer.setTempoInBPM(120);
            }
                   
        }
    }



    public class SaveListener implements ActionListener {

        public void actionPerformed(ActionEvent a) {
            boolean[] checkboxState = new boolean[256];
            
            for(int i = 0;i<256;i++){
                JCheckBox check = (JCheckBox) checkboxList.get(i);
                if(check.isSelected()){
                    checkboxState[i] = true;
                }
                else{
                    checkboxState[i] = false;
                }

              
               
                
               
                
                try{
                    FileOutputStream fs = new FileOutputStream(new File("CheckBox.ser"));
                    ObjectOutputStream os = new ObjectOutputStream(fs);
                    os.writeObject(checkboxState);
                  
                }catch(Exception e){
                    e.printStackTrace();
                }
        }
    }
    }

    public class OpenListener implements ActionListener {
        

        public void actionPerformed(ActionEvent b) {
                        boolean[] checkboxState =null;
                                   try{
                    FileInputStream fileStream = new FileInputStream("CheckBox.ser");
                    ObjectInputStream os = new ObjectInputStream(fileStream);
                    checkboxState = (boolean[]) os.readObject();
                }catch(Exception e){
                    e.printStackTrace();
                }
            
            for(int i = 0;i<256;i++){
                JCheckBox check = (JCheckBox) checkboxList.get(i);
                if(checkboxState[i]){
                    check.setSelected(true);
                }
                else{
                    check.setSelected(false);
                }
            }
            sequencer.stop();
            buildTrackAndStart();



            
        }
    }



    public class MyDownTempoListener implements ActionListener {

        public void actionPerformed(ActionEvent c) {
            float tempoFactor = sequencer.getTempoFactor();
           
            sequencer.setTempoFactor((float) (tempoFactor * .97));
        }
    }

    public class MyUpTempoListener implements ActionListener {

        public void actionPerformed(ActionEvent d) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * 1.03));
        }
    }

    public class MyStopListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            sequencer.stop();
            
        }
    }

    public class MyStartListener implements ActionListener {

       public void actionPerformed(ActionEvent f){
           buildTrackAndStart();
           
       }
    }

    public void buildTrackAndStart() {
        int[] trackList = null;
        
        sequence.deleteTrack(track);
        track = sequence.createTrack();
        
        
        for(int i = 0; i<16;i++){
            trackList = new int[16];
            
            int key = instruments[i];
            
            for(int j = 0;j<16;j++){
                JCheckBox jc = (JCheckBox) checkboxList.get(j + (16*i));
                if(jc.isSelected()){
                    trackList[j] = key;
                }
                else{
                    trackList[j] = 0;
                }
                
            }
            
            
            makeTracks(trackList);
            track.add(makeEvent(176,1,127,0,16));
        }
        
        track.add(makeEvent(192,9,1,0,15));
        try{
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (Exception ex) {
           ex.printStackTrace();
       }
    }

    public void makeTracks(int[] list) {
        for(int i = 0;i<16;i++){
            int key = list[i];
            if (key != 0){
                track.add(makeEvent(144,9,key,100,i));
                track.add(makeEvent(128,9,key,100,i+1));
            }
        }
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
         MidiEvent event = null;
         try{
             ShortMessage a = new ShortMessage();
             a.setMessage(comd,chan,one,two);
             event = new MidiEvent(a, tick);
         } catch (Exception ex) {
           ex.printStackTrace();
       }
       return event;
    }   
}