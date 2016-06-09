
package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class servergui extends javax.swing.JFrame {
public Thread readThread;
public Thread loginThread;
public Thread sendThread;
public Thread getThread;
public Thread dirThread;
public    int port=2345;
public	ServerSocket server=null;
public	Socket socket=null;                  // socket connection to client
public	DataInputStream input=null;          // stream in from client
public	DataOutputStream output=null;        // stream out to client
public	Hashtable files = new Hashtable();   // file storage
public  String Cl = null;
public  String BasePath = "D:\\";
public int chunksize = 10024;

    public servergui() {
        initComponents();
    }

    private void ensureEventThread() {
         if(SwingUtilities.isEventDispatchThread()) {
      return;
    }
    throw new RuntimeException("file");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jFileChooser1 = new javax.swing.JFileChooser();
        txtport = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        basepath = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Dirlist = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();

        jDialog1.setName("jDialog1"); // NOI18N

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));

        jButton1.setBackground(new java.awt.Color(51, 255, 51));
        jButton1.setFont(new java.awt.Font("Raleway", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/server/Apps-system-software-update-icon.png"))); // NOI18N
        jButton1.setText("Start");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setFont(new java.awt.Font("Raleway", 1, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/server/Button-Close-icon.png"))); // NOI18N
        jButton2.setText("Stop");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jFileChooser1.setName("jFileChooser1"); // NOI18N

        txtport.setName("txtport"); // NOI18N
        txtport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtportActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Unicode MS", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FTP Server");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Port");
        jLabel2.setName("jLabel2"); // NOI18N

        basepath.setText("Server's Default Directory");
        basepath.setName("basepath"); // NOI18N
        basepath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basepathActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(153, 153, 153));
        jButton3.setText("jButton3");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        Dirlist.setName("Dirlist"); // NOI18N
        jScrollPane2.setViewportView(Dirlist);

        jLabel3.setText("Server's Directory");
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtport, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(basepath, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(341, 341, 341))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtport, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(basepath, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(!txtport.getText().isEmpty()){
            port = Integer.parseInt(txtport.getText());
        }
        startserver();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        readThread.stop();

                  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtportActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtportActionPerformed

    private void basepathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basepathActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_basepathActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(this);
        
    if(returnVal == JFileChooser.APPROVE_OPTION) {
        setTextSafely("Base Path set to" +
            chooser.getSelectedFile().getPath());
       basepath.setText(chooser.getSelectedFile().getPath());
       BasePath = chooser.getSelectedFile().getPath();
       updateBaseDirList(BasePath);
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new servergui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList Dirlist;
    private javax.swing.JTextField basepath;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtport;
    // End of variables declaration//GEN-END:variables

    private void startserver() {
          ensureEventThread();
    Runnable readRun = new Runnable() {
      public void run() {

        try{
                       setTextSafely("Starting server on port->"+port);
                       server = new ServerSocket(port);
                   	    while (true) {   
                        setTextSafely("Waiting for client.");

                        try {
                            socket = server.accept();
                            if (socket.isConnected())
                            {
                                 Cl = socket.getRemoteSocketAddress().toString();
                            setTextSafely("Clinet Connected form: \n"+Cl);
                             }
                             input = new DataInputStream(socket.getInputStream());
                             output = new DataOutputStream(socket.getOutputStream());
                             boolean shutdown = false;
                             
                             while(shutdown==false){
                                 setTextSafely("Waiting for Commands");
                                 String cmd = new String();
                                 while(shutdown==false){
                                    cmd =  input.readUTF();
                                    if(!cmd.isEmpty()){
                                        break;
                                    }
                                    cmd =null;
                                 }

                                setTextSafely("\n Got command "+cmd);
                                String comand_text=cmd.toString(); 
                                if(comand_text.equalsIgnoreCase("BYE")){
                                    setTextSafely("Remote Client closed connection...");
                                    shutdown=true;
                                }
                                if(comand_text.equalsIgnoreCase("hello")){
                                    setTextSafely("looks like some one is trying to login....");
                 
                                }
                                if(comand_text.equalsIgnoreCase("dir")){
                                    setTextSafely("got list command");
                                    dir(input, output);
                                }
                                if(comand_text.equalsIgnoreCase("put")){
                                    setTextSafely("got put command");
                                    getFile(input, output);
                                }
                                if(comand_text.equalsIgnoreCase("get")){
                                    
                                    setTextSafely("got get command");
                                    sendFile(input, output);
                                }
                                
                             }
                            
                        }catch(Exception e){
                            setTextSafely("main thread..."+e.toString());
                            e.printStackTrace();
                        }

                        }
        }catch(Exception e){
                    this.stop();



        } finally {

          }

      }
    public void   stop(){
         if(!server.isClosed()){
             setTextSafely("server has not started yet");
         } else {
                    try {
                         setTextSafely("Stoping.... server");
                        server.close();
                         setTextSafely("server stoped");
                    } catch (IOException ex) {
                       setTextSafely("Error stoping server.... "+ ex.toString());
                       ex.printStackTrace();
                    }
         }

      }


    };
    readThread = new Thread(readRun);
    readThread.start();
    }
    private void setTextSafely(final String t) {
    Runnable r = new Runnable() {
      public void run() {
        try {
          setText(t);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    };
    SwingUtilities.invokeLater(r);
  }
  private void RemoteLogin(final DataInputStream i, final DataOutputStream o){
     // ensureEventThread();
       Runnable loginRun = new Runnable() {
      public void run() {
                      setTextSafely("Login started");
        try{
        }catch(Exception e){
            setTextSafely("Error in comunications...."+e.getLocalizedMessage());
        } finally {

          }

      }

            private boolean R_login(String UserName, String Password) {
                throw new UnsupportedOperationException("Not yet implemented");
            }


    };
   loginThread = new Thread (loginRun);
   loginThread.run();
  }

    private void getFile(final DataInputStream i, final DataOutputStream o){
     Runnable getFileRun = new Runnable() {
      public void run() {
                  try{
                       setTextSafely("starting to recive file");
                       long start = System.currentTimeMillis();
                       int bytesRead;
                       int current = 0;
                       setTextSafely("Connecting...");
                       int filesize = i.readInt();
                       byte [] mybytearray  = new byte [filesize];
                       FileOutputStream fos = new FileOutputStream(BasePath+"\\"+i.readUTF());
                       setTextSafely("opend the file");
                       BufferedOutputStream bos = new BufferedOutputStream(fos);
                       byte bytes[] = new byte[chunksize];
                       int writecount;
                       while (filesize > 0) {
                            writecount = (filesize >= chunksize ? chunksize : (int)filesize);
                            int b = i.read(bytes, 0, writecount);
                            System.out.println(b);
                            bos.write(bytes, 0,  b);
                            filesize = filesize - b;
                            }
                        bos.flush();
                        long end = System.currentTimeMillis();
                        System.out.println(end-start);    
                        setTextSafely("stored the file");
                        updateBaseDirList(BasePath);
                        bos.close();
                     }catch(Exception e){
                   
                      setTextSafely("Error in comunications...."+e.getLocalizedMessage());
                     } finally {
                 }
           }
    };
   getThread = new Thread (getFileRun);
   getThread.run();
  }

    private void sendFile(final DataInputStream i, final DataOutputStream o){
     Runnable sendFileRun = new Runnable() {
      public void run() {
                      setTextSafely("starting to send file");
        try{

                        File myFile = new File (BasePath+"\\"+i.readUTF());
                        byte [] mybytearray  = new byte [(int)myFile.length()];
                        FileInputStream fis = new FileInputStream(myFile);
                        BufferedInputStream bis = new BufferedInputStream(fis);
                        long lenth=bis.available();
                        o.writeInt((int)lenth);
                        //o.writeUTF(localSelction);
                        setTextSafely("Sending...");
                        byte bytes[] = new byte[chunksize];
                        int writecount;
                        while (lenth > 0) {
                        writecount = (lenth >= chunksize ? chunksize : (int)lenth);
                            int a = bis.read(bytes, 0, writecount);
                            System.out.println(a);
                            o.write(bytes, 0,  a);
                            lenth = lenth - a;
                        }

                            setTextSafely(" the file");
                        }catch(Exception e){
                        try {
                        o.writeUTF("NCK");
                         } catch (IOException ex) {
                        Logger.getLogger(servergui.class.getName()).log(Level.SEVERE, null, ex);
                         }
            setTextSafely("Error in comunications...."+e.getLocalizedMessage());
        } finally {
          }
      }
    };
   sendThread = new Thread (sendFileRun);
   sendThread.run();
  }

 private void dir(final DataInputStream i, final DataOutputStream o){
     Runnable dirRun = new Runnable() {
      public void run() {
                      setTextSafely("starting to list Dir");
        try{
                       File path = new File(BasePath);
                       File Filelist [];
                       Filelist = path.listFiles();
                       o.writeInt(Filelist.length);
                            for (int i = 0, n = Filelist.length; i < n; i++) {
                             setTextSafely(Filelist[i].toString());
                             o.writeUTF(Filelist[i].getName());
                            }
                            setTextSafely("finishe List dir");
        }catch(Exception e){                   
            setTextSafely("Error in comunications...."+e.getLocalizedMessage());
        } finally {
          }
      }
    };
   dirThread = new Thread (dirRun);
   dirThread.run();
  }


  private void setText(String t) {
    ensureEventThread();
    jTextArea1.setText(jTextArea1.getText()+"\n"+t);
  }

    private void updateBaseDirList(String BasePath) {
        File path = new File(BasePath);
        File Filelist [];
        Filelist = path.listFiles();
        String[] str = new String [Filelist.length];
        for(int k =0;k<str.length; k++){
            str[k] = Filelist[k].getName();
        }
        fillList(str);
    }

  private void fillList(String items[]) {
                 ensureEventThread();
                 Dirlist.removeAll();
                 Dirlist.setListData(items);
   }
   private void setListSafely(final String t[]) {
    Runnable r = new Runnable() {
      public void run() {
        try {
          fillList(t);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    };
    SwingUtilities.invokeLater(r);
  }



}
