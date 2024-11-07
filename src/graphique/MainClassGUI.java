package graphique;

import java.awt.*;
import java.awt.event.*;
import java.security.*;
import java.sql.SQLException;
import java.text.*;
import javax.swing.*;
import java.util.Date;
import java.util.regex.*;

import com.jdbc.main.MainClass;
import com.jdbc.model.Employee;
import com.jdbc.service.DatabaseService;

public class MainClassGUI extends JFrame {

    private JTextField nomTextField;
    private JTextField prenomTextField;
    private JTextField telTextField;
    private JTextField dateNaissanceTextField;
    private JTextField mailTextField;
    private JTextField passwordTextField;

    private DatabaseService databaseService;

    public MainClassGUI() {
        super("Gestion des Employés");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        databaseService = new DatabaseService();

        JPanel formPanel = new JPanel(new GridBagLayout());
        getContentPane().add(formPanel);

        formPanel.add(new JLabel("Nom : "), createGridBagConstraints(0, 0, 1, 1));
        nomTextField = new JTextField(20);
        formPanel.add(nomTextField, createGridBagConstraints(1, 0, 1, 1));

        formPanel.add(new JLabel("Prénom : "), createGridBagConstraints(0, 1, 1, 1));
        prenomTextField = new JTextField(20);
        formPanel.add(prenomTextField, createGridBagConstraints(1, 1, 1, 1));

        formPanel.add(new JLabel("Numéro Tel : "), createGridBagConstraints(0, 2, 1, 1));
        telTextField = new JTextField(20);
        formPanel.add(telTextField, createGridBagConstraints(1, 2, 1, 1));

        formPanel.add(new JLabel("Date de Naissance : "), createGridBagConstraints(0, 3, 1, 1));
        dateNaissanceTextField = new JTextField(20);
        formPanel.add(dateNaissanceTextField, createGridBagConstraints(1, 3, 1, 1));

        formPanel.add(new JLabel("E-mail : "), createGridBagConstraints(0, 4, 1, 1));
        mailTextField = new JTextField(20);
        formPanel.add(mailTextField, createGridBagConstraints(1, 4, 1, 1));

        formPanel.add(new JLabel("Mot de Passe : "), createGridBagConstraints(0, 5, 1, 1));
        passwordTextField = new JPasswordField(20);
        formPanel.add(passwordTextField, createGridBagConstraints(1, 5, 1, 1));

        JButton insertButton = new JButton("Insérer");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertEmployee();
            }
        });
        formPanel.add(insertButton, createGridBagConstraints(0, 6, 2, 1));

        JButton selectAllButton = new JButton("Select All");
        selectAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                databaseService.getAllEmployees();
            }
        });
        formPanel.add(selectAllButton, createGridBagConstraints(0, 7, 2, 1));

        JButton selectByIdButton = new JButton("Select Employé par Id");
        selectByIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString = JOptionPane.showInputDialog(MainClassGUI.this, "Entrez l'Id de l'employé :");
                if (idString != null) {
                    try {
                        int id = Integer.parseInt(idString);
                        try {
							databaseService.choisirEmployeeParId(id);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainClassGUI.this, "Id invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        formPanel.add(selectByIdButton, createGridBagConstraints(0, 8, 2, 1));

        JButton deleteButton = new JButton("Supprimer Employé");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString = JOptionPane.showInputDialog(MainClassGUI.this, "Entrez l'Id de l'employé à supprimer :");
                if (idString != null) {
                    try {
                        int id = Integer.parseInt(idString);
                        databaseService.supprimerEmployeeById(id);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainClassGUI.this, "Id invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        formPanel.add(deleteButton, createGridBagConstraints(0, 9, 2, 1));

        JButton updateButton = new JButton("Mettre à jour l'employé");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString = JOptionPane.showInputDialog(MainClassGUI.this, "Entrez l'Id de l'employé à mettre à jour :");
                if (idString != null) {
                    try {
                        int id = Integer.parseInt(idString);
                        boolean isFound = false;
						try {
							isFound = databaseService.choisirEmployeeParId(id);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                        if (isFound) {
                            updateEmployee(id);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainClassGUI.this, "Id invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        formPanel.add(updateButton, createGridBagConstraints(0, 10, 2, 1));

        JButton exitButton = new JButton("Quitter");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        formPanel.add(exitButton, createGridBagConstraints(0, 11, 2, 1));

        pack();
        setVisible(true);
    }

    private GridBagConstraints createGridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        return gbc;
    }

    private void insertEmployee() {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String telString = telTextField.getText();
        String dateNaissanceString = dateNaissanceTextField.getText();
        String mail = mailTextField.getText().trim();
        String password = passwordTextField.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || telString.isEmpty() || dateNaissanceString.isEmpty() || mail.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Vérification du format de l'e-mail
        if (!MainClass.isValidEmail(mail)) {
            JOptionPane.showMessageDialog(this, "Adresse e-mail invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Vérification du format du mot de passe
        if (!MainClass.isValidPassword(password)) {
            JOptionPane.showMessageDialog(this, "Mot de passe invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String hashedPassword = MainClass.hashPassword(password);

        int tel = 0;
        try {
            tel = Integer.parseInt(telString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Numéro de téléphone invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date dateNaissance = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateNaissance = dateFormat.parse(dateNaissanceString);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Format de date invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Employee employee = new Employee(nom, prenom, tel, dateNaissance, mail, hashedPassword);
        databaseService.insertEmployee(employee);
    }

    private void updateEmployee(int id) {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String telString = telTextField.getText();
        String dateNaissanceString = dateNaissanceTextField.getText();
        String mail = mailTextField.getText();
        String password = passwordTextField.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || telString.isEmpty() || dateNaissanceString.isEmpty() || mail.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Vérification du format de l'e-mail
        if (!MainClass.isValidEmail(mail)) {
            JOptionPane.showMessageDialog(this, "Adresse e-mail invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
     // Vérification du format du mot de passe
        if (!MainClass.isValidPassword(password)) {
            JOptionPane.showMessageDialog(this, "Mot de passe invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String hashedPassword = MainClass.hashPassword(password);

        int tel = 0;
        try {
            tel = Integer.parseInt(telString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Numéro de téléphone invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date dateNaissance = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateNaissance = dateFormat.parse(dateNaissanceString);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Format de date invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Employee employee = new Employee(id, nom, prenom, tel, dateNaissance, mail, hashedPassword);
        databaseService.updateEmployee(employee);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainClassGUI();
            }
        });
    }
}
