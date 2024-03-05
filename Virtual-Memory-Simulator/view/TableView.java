package org.example.view;

import org.example.logic.SimulationManager;
import org.example.model.MyPMTable;
import org.example.model.MyPageTable;
import org.example.model.MyTLB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;
public class TableView extends JFrame implements ActionListener {

    JTextArea info,pageTextArea,offsetTextArea;
    JPanel setup;
    JTable tlbJTable,pageTableJTable,pmTableJTable;
    JScrollPane scroll1,scroll2,scroll3 ;
    JButton next,forward;
    JTextField pageSizeTextField,tlbTextField,instrTextField,genInstrTextField,genTextField,offsetTextField,virtMemSizeTextField;
    JButton reset1,submit1,instrGen,submit2;
    JRadioButton fifo,lru;
    MyPageTable tableModel2;
    MyTLB tableTLB;
    MyPMTable tableModel3;
    int alg=0,simulationStep=0,offset=0,address=0,addressLength=0;
    SimulationManager sim;
    String pageHex;
   // int[] array = new int[10];
    String[] hexArray=new String[15];
    int tlbHit=0,pageTableHit=0;
    public TableView() {
        getContentPane().setBackground(Color.WHITE);
        this.setTitle("");
        this.setLayout(null);
        this.setBounds(0, 0, 1700, 1000);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        info = new JTextArea();
        info.setBounds(20, 40, 300, 300);
        info.setEditable(false);
        info.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        info.setBackground(new Color(240, 240, 240));
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        Font largerFont = new Font("Arial", Font.PLAIN, 15);
        info.setFont(largerFont);
        add(info);

        JLabel instrBreakdownLabel=new JLabel("Address breakdown");
        instrBreakdownLabel.setBounds(45,450,300,60);
        Font largerFont2 = new Font("Arial", Font.PLAIN, 18);
        instrBreakdownLabel.setFont(largerFont2);
        add(instrBreakdownLabel);

        JLabel pageLabel=new JLabel("Page:");
        pageLabel.setBounds(30,500,150,50);
        add(pageLabel);

        pageTextArea=new JTextArea();
        pageTextArea.setBounds(80,500,150,40);
        pageTextArea.setEditable(false);
        pageTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pageTextArea.setBackground(new Color(240, 240, 240));
        pageTextArea.setLineWrap(true);
        pageTextArea.setWrapStyleWord(true);
        Font largerFont3 = new Font("Arial", Font.PLAIN, 18);
        info.setFont(largerFont3);
        add(pageTextArea);

        JLabel offsetJLabel=new JLabel("Offset");
        offsetJLabel.setBounds(30,560,150,50);
        add(offsetJLabel);

        offsetTextArea=new JTextArea();
        offsetTextArea.setBounds(80,560,150,40);
        offsetTextArea.setEditable(false);
        offsetTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        offsetTextArea.setBackground(new Color(240, 240, 240));
        offsetTextArea.setLineWrap(true);
        offsetTextArea.setWrapStyleWord(true);
        Font largerFont4 = new Font("Arial", Font.PLAIN, 18);
        info.setFont(largerFont4);
        add(offsetTextArea);

        tableTLB=new MyTLB();
        tlbJTable=new JTable(tableTLB);
        scroll1=new JScrollPane(tlbJTable);
        scroll1.setBounds(400,40,350,350);
        add(scroll1);

        JLabel tlbName=new JLabel("Translation Lookaside Buffer");
        tlbName.setBounds(400,0,300,55);
        add(tlbName);

        tableModel2 = new MyPageTable();
        pageTableJTable = new JTable(tableModel2);
        scroll2 = new JScrollPane(pageTableJTable);
        scroll2.setBounds(400, 450, 350, 350);
        add(scroll2);

        JLabel pgName=new JLabel("Page Table");
        pgName.setBounds(400,400,300,55);
        add(pgName);

        tableModel3 = new MyPMTable();
        pmTableJTable = new JTable(tableModel3);
        scroll3 = new JScrollPane(pmTableJTable);
        scroll3.setBounds(770, 450, 350, 350);
        add(scroll3);

        JLabel pmName=new JLabel("Physiscal Memory");
        pmName.setBounds(770,400,300,55);
        add(pmName);

        next=new JButton("Next");
        next.setBounds(20,350,100,50);
        next.addActionListener(this);
        add(next);

        forward=new JButton("Forward");
        forward.setBounds(220,350,100,50);
        forward.addActionListener(this);
        //add(forward);

        //////////////////////////////////////////////////////////////////

        setup=new JPanel();
        setup.setLayout(null);
        setup.setBounds(800,20,700,380);
        add(setup);

        JLabel lbl1=new JLabel("Please introduce the setup of the simulation");
        lbl1.setBounds(220,20,350,60);
        Font font = new Font("Arial", Font.BOLD, 16); // You can adjust the font family, style, and size
        lbl1.setFont(font);
        setup.add(lbl1);

        JLabel pageSizeLb=new JLabel("Physical Page Size:");
        pageSizeLb.setBounds(20,100,150,30);
        setup.add(pageSizeLb);

        pageSizeTextField = new JTextField();
        pageSizeTextField.setBounds(150,100,150,30);
        setup.add(pageSizeTextField);

        JLabel tlbSizeLb=new JLabel("TLB Size:");
        tlbSizeLb.setBounds(20,150,150,30);
        setup.add(tlbSizeLb);

        tlbTextField= new JTextField();
        tlbTextField.setBounds(150,150,150,30);
        setup.add(tlbTextField);

        JLabel offsetLbl=new JLabel("Offset:");
        offsetLbl.setBounds(20,200,150,30);
        setup.add(offsetLbl);

        JLabel virtualMemLbl=new JLabel("Virtual Memory Size:");
        virtualMemLbl.setBounds(20,250,150,30);
        setup.add(virtualMemLbl);

        offsetTextField=new JTextField();
        offsetTextField.setBounds(150,200,150,30);
        setup.add(offsetTextField);

        virtMemSizeTextField=new JTextField();
        virtMemSizeTextField.setBounds(150,250,150,30);
        setup.add(virtMemSizeTextField);

        reset1=new JButton("Reset");
        reset1.setBounds(250,300,100,50);
      //  setup.add(reset1);
        submit1=new JButton("Submit");
        submit1.setBounds(100,300,100,50);
        submit1.addActionListener(this);
        reset1.addActionListener(this);
        setup.add(submit1);

        submit2=new JButton("Submit addresses");
        submit2.setBounds(400,300,150,50);
        submit2.addActionListener(this);
        setup.add(submit2);

        JLabel instr=new JLabel("Address");
        instr.setBounds(350,100,150,30);
        setup.add(instr);

        instrTextField=new JTextField();
        instrTextField.setBounds(450,100,150,30);
        setup.add(instrTextField);

        instrGen=new JButton("Generate addresses");
        instrGen.setBounds(310,150,170,30);
        instrGen.addActionListener(this);
        setup.add(instrGen);

        genTextField=new JTextField();
        genTextField.setBounds(490,150,200,30);
        setup.add(genTextField);

        fifo = new JRadioButton("FIFO");
        lru = new JRadioButton("LRU");
        fifo.setBounds(400,200,100,30);
        lru.setBounds(520,200,100,30);
        ButtonGroup group = new ButtonGroup();
        group.add(fifo);
        group.add(lru);
        // setup.add(fifo);
      //  setup.add(lru);
        fifo.addActionListener(this);
        lru.addActionListener(this);



        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String instruction;
        int virtualMemSize=Integer.parseInt(virtMemSizeTextField.getText());
        offset= Integer.parseInt(offsetTextField.getText());
        int physicalPageSize=0, tlbSize=0 ;
        if(e.getActionCommand().equals("Generate addresses")){

            int min = 0;   // Minimum value for the interval
            int max = (int)(Math.pow(2,addressLength))-1;
            System.out.println("max is "+max);
            Random random = new Random();
            hexArray=new String[10];
            for (int i = 0; i < 10; i++) {
                int randomNumber = random.nextInt(max - min + 1) + min;
                String hexString = Integer.toHexString(randomNumber);
                hexArray[i] = hexString.toUpperCase();
            }

            StringBuilder arrayString = new StringBuilder();
            for (int i=0;i<10;i++) {
                System.out.print(hexArray[i]+" ");
                arrayString.append(hexArray[i]).append(" ");
            }
            System.out.println();
            genTextField.setText(arrayString.toString());
        }
        if(e.getActionCommand().equals("Submit addresses")){

            System.out.println("submit address");

            if(!genTextField.getText().isEmpty()){
                hexArray = genTextField.getText().split(" ");
            }
            if (!instrTextField.getText().isEmpty()) {
                instruction = instrTextField.getText();
                hexArray = addValueToArray(hexArray, instruction);
            }else{
                StringBuilder first=new StringBuilder();
                first.append(hexArray[0]);
                instrTextField.setText(first.toString());
            }
            sim=new SimulationManager(physicalPageSize,tlbSize,hexArray[0],hexArray,tableTLB,tableModel2,tableModel3,addressLength);
            sim.runSimulation();
            if (hexArray.length > 0) {
                next.setEnabled(true);
            }
        }
        if(e.getActionCommand().equals("Submit")){

            physicalPageSize = Integer.parseInt(pageSizeTextField.getText());
            tlbSize = Integer.parseInt(tlbTextField.getText());
            //offset= Integer.parseInt(offsetTextField.getText());

            int table2Size,table3Size;
            table2Size=(int)(virtualMemSize/Math.pow(2,offset));
            table3Size=(int)(physicalPageSize/Math.pow(2,offset));

            addressLength=log2(virtualMemSize);

            tableModel3.setSize((int)(physicalPageSize/Math.pow(2,offset)));
            tableTLB.setSize(tlbSize);
            tableModel2.setSize((int)(virtualMemSize/Math.pow(2,offset)));

            System.out.println(tlbSize+" "+physicalPageSize);
            for (int i = 0; i < hexArray.length; i++) {
                System.out.print(hexArray[i]+ " ");
            }

            System.out.println(alg);

            StringBuilder infoText=new StringBuilder();
            infoText.append("Offset= ").append(offset).append(" (points to desired data in page frame)").append("\n");
            infoText.append("TLB rows= ").append(tlbSize).append("\n");
            infoText.append("Physical Page Rows=").append(physicalPageSize).append(" / 2^").append(offset).append(" = ").append(table3Size).append("\n");
            infoText.append("Page Table Rows=").append(virtualMemSize).append(" / ").append(" 2^").append(offset).append(" = ").append(table2Size);

            info.setText(infoText.toString());

        }

        if(e.getActionCommand().equals("Next")) {
            String binaryRep,page,offsetBinary;
            binaryRep=sim.hexToBinary(hexArray[0],addressLength);
            offsetBinary = binaryRep.substring(binaryRep.length() - offset);

            page = binaryRep.substring(0, binaryRep.length() - offset);
            pageHex=sim.binaryToHex(page);

            pageTextArea.setText(page);
            offsetTextArea.setText(offsetBinary);

          //  info.setText("");


            if (simulationStep == 0) {
                StringBuilder info1 = new StringBuilder();
                info1.append("The requested address is broken down into page and offset.\n").append(hexArray[0]).append(" has the binary representation: ").append(sim.hexToBinary(hexArray[0],addressLength));
                info.setText(info1.toString());
                instrTextField.setText(hexArray[0]);
                System.out.println("step 0- "+simulationStep);
                tlbHit=0;
                pageTableHit=0;
            }

            if (simulationStep == 1) {
                StringBuilder info1 = new StringBuilder();
                info1.append("The requested page is searched in TLB");
                info.setText(info1.toString());
                System.out.println("step 1 -"+simulationStep);
            }

            if (simulationStep == 2) {
                System.out.println("step 2 -"+simulationStep);
                if (tableTLB.searchTLB(page) == -1)
                    info.setText("TLB miss!");
                else {
                    info.setText("TLB hit!\nData will be loaded from TLB.");
                    tlbHit=1;
                    simulationStep=4;
                }
            }

            if (simulationStep == 3 && tlbHit==0) {
                System.out.println("step 3- "+simulationStep);
                info.setText("The requested address is searched in Page Table");
            }

            if (simulationStep == 4 && tlbHit==0) {
                System.out.println("step 4 - "+simulationStep);
                if (tableModel2.searchPageTable(sim.binaryToHex(page)) == -1) {
                    info.setText("Page Table miss!\nData will be uploaded from Secondary Memory and TLB, Page Table, and Physical Memory will be updated.");
                    int index;
                    index = tableModel3.addValue(page, offset);

                    tableModel2.setValue("1", Integer.parseInt(pageHex, 16), 1);
                    tableModel2.setValue(String.valueOf(index), Integer.parseInt(pageHex, 16), 2);

                    tableTLB.addValue(page, Integer.toHexString(index).toUpperCase());
                }
                else{
                    info.setText("Page Table hit!\nData will be loaded from Page Table.");
                }
            }

            if (simulationStep == 5) {
                System.out.println("step 5 - "+simulationStep);
                info.setText("The simulation finished\n Get to the next address");
                address++;
                if (hexArray.length > 1) {
                    simulationStep=7;
                    String[] newArray = new String[hexArray.length - 1];
                    System.arraycopy(hexArray, 1, newArray, 0, newArray.length);

                    for (int i = 0; i < newArray.length; i++) {
                        System.out.print(newArray[i] + " ");
                    }
                    hexArray = newArray;
                    System.out.println();
                    } else {

                    System.out.println("No more addresses in the array");
                   // info.setText("No more addresses in the array");
                   // next.setEnabled(false);
                  //  JOptionPane.showMessageDialog(null, "Please generate new addresses and submit them!", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println("step 5 - "+simulationStep);
            }
            if(simulationStep==6){
                info.setText("No more addresses in the array");
                next.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Please generate new addresses and submit them!", "Message", JOptionPane.INFORMATION_MESSAGE);
            }

            if (simulationStep < 6) {
                simulationStep++;
            } else
                simulationStep = 0;
            System.out.println("end " + simulationStep);
        }
    }

    private String[] addValueToArray(String[] array, String value) {
        String[] newArray = new String[array.length + 1];
        newArray[0] = value;
        System.arraycopy(array, 0, newArray, 1, array.length);
        return newArray;
    }
    public int log2(double num) {
        return (int) (Math.log(num) / Math.log(2));
    }
}

