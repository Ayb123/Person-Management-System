package com.jdbc.main;

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.jdbc.model.Employee;
import com.jdbc.service.DatabaseService;

public class MainClass {

    public static void main(String[] args) {

        int choice;
        boolean isRunning = true;

        DatabaseService databaseService = new DatabaseService();

        try (Scanner scanner = new Scanner(System.in)) {

            while (isRunning) {
                System.out.println("Choisissez Votre Choix : ");
                System.out.println("1. Inserer ");
                System.out.println("2. Selectionner tout ");
                System.out.println("3. Select Employés par un Id ");
                System.out.println("4. Supprimer Employé ");
                System.out.println("5. Mettre à jour l'employé ");
                System.out.println("6. Quitter ");

                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    // Insertion de l'employé :
                    case 1:
                        System.out.println("Saisissez votre Nom : ");
                        String nom = scanner.nextLine();

                        System.out.println("Saisissez votre Prénom : ");
                        String prenom = scanner.nextLine();

                        int tel = 0;
                        boolean isTelValid = false;
                        do {
                            System.out.println("Saisissez votre Numéro Tel (10 chiffres) : ");
                            String telString = scanner.nextLine();
                            if (telString.matches("\\d{10}")) {
                                tel = Integer.parseInt(telString);
                                isTelValid = true;
                            } else {
                                System.out.println("Numéro de téléphone invalide ! Veillez saisir un autre numéro de téléphone");
                            }
                        } while (!isTelValid);

                        String dateString;
                        Date dateAnniv = null;
                        boolean isDateValid = false;

                        while (!isDateValid) {
                            System.out.println("Entrez votre date de naissance (format dd/MM/yyyy) : ");
                            dateString = scanner.nextLine();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                            try {
                                dateAnniv = dateFormat.parse(dateString);
                                isDateValid = true;
                            } catch (ParseException e) {
                                System.out.println("Format de date invalide ! Veillez saisir une autre date");
                            }
                        }

                        String mail;
                        boolean isMailValid = false;
                        do {
                            System.out.println("Saisissez votre Mail : ");
                            mail = scanner.nextLine();
                            if (isValidEmail(mail)) {
                                isMailValid = true;
                            } else {
                                System.out.println("Adresse e-mail invalide ! Veillez saisir une autre adresse e-mail");
                            }
                        } while (!isMailValid);

                        System.out.println("Saisissez votre mot de passe : ");
                        String password = scanner.nextLine();
                        boolean isPasswordValid = isValidPassword(password);

                        while (!isPasswordValid) {
                            System.out.println("Le mot de passe doit contenir au moins 12 caractères, un chiffre, un caractère spécial et une lettre majuscule.");
                            System.out.println("Veuillez corriger le mot de passe : ");
                            password = scanner.nextLine();
                            isPasswordValid = isValidPassword(password);
                        }

                        // Mot de passe valide, procéder à l'insertion
                        databaseService.insertEmployee(new Employee(nom, prenom, tel, dateAnniv, mail, hashPassword(password)));

                        break;

                    case 2:
                        databaseService.getAllEmployees();
                        break;

                    case 3:
                        System.out.println("Choisissez l'Id de l'employé : ");
                        databaseService.choisirEmployeeParId(Integer.parseInt(scanner.nextLine()));
                        break;

                    case 4:
                        System.out.println("Choisissez l'Id de l'employé que vous voulez supprimer : ");
                        databaseService.supprimerEmployeeById(Integer.parseInt(scanner.nextLine()));
                        break;

                    case 5:
                        // Mettre à jour l'employé
                        System.out.println("Choisissez l'Id de l'employé que vous voulez modifier : ");
                        int idUpdated = Integer.parseInt(scanner.nextLine());
                        boolean isFound = databaseService.choisirEmployeeParId(idUpdated);
                        if (isFound) {
                            System.out.println("Saisissez votre Nouveau Nom : ");
                            String nomUpdated = scanner.nextLine();

                            System.out.println("Saisissez votre Nouveau Prénom : ");
                            String prenomUpdated = scanner.nextLine();

                            int telUpdated = 0;
                            boolean isTelUpdatedValid = false;
                            do {
                                System.out.println("Saisissez votre Nouveau Numéro Tel (10 chiffres) : ");
                                String telUpdatedString = scanner.nextLine();
                                if (telUpdatedString.matches("\\d{10}")) {
                                    telUpdated = Integer.parseInt(telUpdatedString);
                                    isTelUpdatedValid = true;
                                } else {
                                    System.out.println("Numéro de téléphone invalide !");
                                }
                            } while (!isTelUpdatedValid);

                            String dateStringUpdated;
                            Date dateAnnivUpdated = null;
                            boolean isDateUpdatedValid = false;

                            while (!isDateUpdatedValid) {
                                System.out.println("Entrez votre Nouvelle date de naissance (format dd/MM/yyyy) : ");
                                dateStringUpdated = scanner.nextLine();
                                if (isDateValid(dateStringUpdated)) {
                                    SimpleDateFormat dateFormatUpdated = new SimpleDateFormat("dd/MM/yyyy");
                                    try {
                                        dateAnnivUpdated = dateFormatUpdated.parse(dateStringUpdated);
                                        isDateUpdatedValid = true;
                                    } catch (ParseException e) {
                                        System.out.println("Format de date invalide !");
                                    }
                                } else {
                                    System.out.println("Format de date invalide !");
                                }
                            }

                            String mailUpdated;
                            boolean isMailUpdatedValid = false;
                            do {
                                System.out.println("Saisissez votre Nouveau Mail : ");
                                mailUpdated = scanner.nextLine();
                                if (isValidEmail(mailUpdated)) {
                                    isMailUpdatedValid = true;
                                } else {
                                    System.out.println("Adresse e-mail invalide !");
                                }
                            } while (!isMailUpdatedValid);

                            String passwordUpdated;
                            boolean isPasswordValidUpdated = false;
                            do {
                                System.out.println("Saisissez votre Nouveau Mot de Passe : ");
                                passwordUpdated = scanner.nextLine();
                                isPasswordValidUpdated = isValidPassword(passwordUpdated);

                                if (!isPasswordValidUpdated) {
                                    System.out.println("Le mot de passe doit contenir au moins 12 caractères, un chiffre, un caractère spécial et une lettre majuscule.");
                                    System.out.println("Veuillez corriger le mot de passe : ");
                                }
                            } while (!isPasswordValidUpdated);

                            Employee updatedEmployee = new Employee(idUpdated, nomUpdated, prenomUpdated, telUpdated, dateAnnivUpdated, mailUpdated, hashPassword(passwordUpdated));
                            databaseService.updateEmployee(updatedEmployee);
                        }
                        break;


                    case 6:
                        System.out.println("Merci pour votre visite. Au revoir !");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("Choix Incorrect ! ");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Il y a eu une erreur lors de l'exécution du programme.", e);
        }

    }
    
    public static boolean isDateValid(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // La date doit être stricte

        try {
            dateFormat.parse(dateString);
            return true; // La date est valide
        } catch (ParseException e) {
            return false; // La date est invalide
        }
    }


    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        // Au moins 12 caractères, un chiffre, un caractère spécial et une lettre majuscule
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{12,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erreur lors du hachage du mot de passe.", e);
        }
    }
    
    

}


