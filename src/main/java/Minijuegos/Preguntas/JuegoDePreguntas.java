package Minijuegos.Preguntas;

/**
 *
 * @author Brahiam
 */
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class JuegoDePreguntas extends javax.swing.JFrame {
    int aciertos = 0;
    static String urlBD = "https://docs.google.com/spreadsheets/d/e/2PACX-1vRzzKW7lD3dJtG2JUU0OO81H7f5ytB8AGh-VBUKC8CspJjTQDYiUPwicRqKQepUQpQHOiMPBp--Kt11/pub?output=tsv";
    static String textoBaseDePreguntas = LeerArchivo_ASCII(urlBD);
    static String[] renglones = textoBaseDePreguntas.split("\n");
    static int cantidadDePreguntas = 10;
    
    static String[][] baseDePreguntas = new String[cantidadDePreguntas][5];
    
    String[] preguntaEscogida;
    String pregunta;
    String respuesta;
    String img;
    ArrayList<String> Opciones = new ArrayList<>();
    int n_pregunta = 0;

    
    public void escogerPregunta(int n) {
        preguntaEscogida = baseDePreguntas[n];
        pregunta = preguntaEscogida[0];
        respuesta = preguntaEscogida[1];
        if (preguntaEscogida.length > 5) {
            img = preguntaEscogida[5];
        } else {
            img = "";
        }
        Opciones.clear();
        for(int i=1;i<preguntaEscogida.length-1;i++){
            Opciones.add(preguntaEscogida[i]);
        }
        for (int i = 0; i < 4; i++) {
            Collections.shuffle(Opciones);
        }
    }
    
    public void mostrarPregunta() {
        jLabel1.setText(pregunta);
        if (img.equals("")) {
            jLabel2.setVisible(false);
        } else {
            jLabel2.setVisible(true);
            jLabel2.setText("");
            try {
                BufferedImage imagen = ImageIO.read(new URL(img));
                Image imagenEscalada = imagen.getScaledInstance(-1, 260, Image.SCALE_SMOOTH);
                jLabel2.setIcon(new ImageIcon(imagenEscalada));
            } catch (IOException e) {
                jLabel2.setText("La imágen no se pudo cargar");
                jLabel2.setIcon(null);
            }
        }
        jButton1.setText(Opciones.get(0));
        jButton2.setText(Opciones.get(1));
        jButton3.setText(Opciones.get(2));
        jButton4.setText(Opciones.get(3));
    }
    
    void escogerRespuesta(int n) {
        if (Opciones.get(n).equals(respuesta)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Su respuesta es correcta",
                    "Muy bien :)",
                    JOptionPane.INFORMATION_MESSAGE
            );
            aciertos++;
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Su respuesta es incorrecta, la respuesta es: " + respuesta,
                    "Que mal :(",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        jugar();
    }

    public void jugar() {
        if (n_pregunta == cantidadDePreguntas) {
            JOptionPane.showMessageDialog(
                    this,
                    "Puntuacion: "+aciertos+"/10",
                    "Juego Terminado",
                    JOptionPane.PLAIN_MESSAGE
            );
            dispose();
        }
        escogerPregunta(n_pregunta);
        mostrarPregunta();
        n_pregunta++;
    }
    
    public JuegoDePreguntas() {
        for(int i = 0;i<renglones.length;i++){
            String renglon = renglones[i];
            baseDePreguntas[i] = renglon.split("\t");
        }
        initComponents();
        setSize(900, 630);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        jugar();
    }    

     public static String LeerArchivo_ASCII(String ruta) {
        try {
            if (ruta == null) {
                throw new RuntimeException("Error, la URL de lectura no puede ser nula");
            }
            URL url = new URL(ruta);
            URLConnection conexión = url.openConnection();
            InputStreamReader isr = new InputStreamReader(conexión.getInputStream());
            return LeerArchivo_ASCII(isr);
        } catch (Exception e) {
            System.out.println("No hay conexión a internet, la base de datos no pudo ser cargada");
            System.exit(0);
        }
        return "";
    }

    public static String LeerArchivo_ASCII(Reader reader) throws Exception {
        BufferedReader br = new BufferedReader(reader);
        String texto = "";
        String linea;
        boolean primerRenglón = true;
        while ((linea = br.readLine()) != null) {
            if (primerRenglón) {
                primerRenglón = false;
            } else {
                texto += "\n";
            }
            texto += linea;
        }
        reader.close();
        br.close();
        return texto;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Gill Sans Ultra Bold Condensed", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pregunta con imagen");

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Gill Sans Ultra Bold Condensed", 0, 18)); // NOI18N
        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Gill Sans Ultra Bold Condensed", 0, 18)); // NOI18N
        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Gill Sans Ultra Bold Condensed", 0, 18)); // NOI18N
        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Gill Sans Ultra Bold Condensed", 0, 18)); // NOI18N
        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        escogerRespuesta(0);
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        escogerRespuesta(1);
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        escogerRespuesta(2);
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        escogerRespuesta(3);
    }                                        

    
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration                   
}
