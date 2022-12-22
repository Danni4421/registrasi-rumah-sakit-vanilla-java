package FINAL;

public class AdminPage {

  public static boolean loginAdmin(String[][] account, String[][] dataPatient, String[][] bookingData) {
    boolean result = false;

    App.Enter(1);
    App.menuToogle("LOGIN ADMIN");
    System.out.print("Masukkan ID Admin : ");
    App.sc.nextLine();
    String idAdmin = App.sc.nextLine();
    if (idAdmin.equals("admin")) {
      System.out.print("Masukkan Password : ");
      String inputPassAdmin = App.sc.nextLine();
      if (inputPassAdmin.equals("8888")) {
        result = true;
      } else {
        System.out.println("Password Tidak Valid");
      }
    } else {
      System.out.println("ID Admin tidak Valid");
    }

    return result;
  }

  public static void checkResult(String[][] account, String[][] bookingData, String[][] dataPatient,
      String[] infoBookingUser1, int[] infoBookingUser2, String[][] string2dInitialized,
      String[][][] string3dInitialized, int[][] kuotaDokter) {
    boolean result = loginAdmin(App.account, App.dataPatient, App.bookingData);

    if (result) {
      AdminUser(App.account, App.bookingData, App.dataPatient, infoBookingUser1, infoBookingUser2, string3dInitialized,
          string2dInitialized, kuotaDokter);
    }
  }

  private static void AdminUser(String[][] account, String[][] bookingData, String[][] dataPatient,
      String[] infoBookingUser1, int[] infoBookingUser2, String[][][] string3dInitialized,
      String[][] string2dInitialized, int[][] kuotaDokter) {
    App.Enter(1);

    boolean resultConfirm = true;

    while (resultConfirm) {
      System.out.println("===================================");
      System.out.println("||      MENU ADMINISTRATOR       ||");
      System.out.println("===================================");
      App.Enter(1);
      System.out.println("1. CHECK AKUN PASIEN");
      System.out.println("2. CHECK DATA DIRI PASIEN");
      System.out.println("3. CHECK PEMESANAN");
      System.out.println("4. UBAH KUOTA DOKTER");
      System.out.println("5. UBAH DOKTER");
      System.out.println("6. UBAH JADWAL DOKTER");
      System.out.print("=> ");
      int inputUser = App.sc.nextInt();

      switch (inputUser) {
        case 1:
          checkAccPatient(account);
          break;
        case 2:
          checkInformationPatient(dataPatient);
          break;
        case 3:
          checkPemesananPasien(infoBookingUser1, infoBookingUser2);
          break;
        case 4:
          changeSlotDokter(kuotaDokter, string2dInitialized, string3dInitialized);
          break;
        case 5:
          changeDokter(string2dInitialized, string3dInitialized);
          break;
        case 6:
          changeScheduleDoktor(string2dInitialized, string3dInitialized);
          break;
      }

      System.out.print("Apakah Anda ingin melakukan cek lagi? [y/n]: ");
      char checkAgain = App.sc.next().charAt(0);

      if (checkAgain == 'y') {
        resultConfirm = true;
      } else {
        resultConfirm = false;
      }

    }
  }

  private static void changeScheduleDoktor(String[][] string2dInitialized, String[][][] string3dInitialized) {
    boolean changeDokterCondition = true;
    while (changeDokterCondition) {
      App.Enter(1);
      System.out.println("===================================");
      System.out.println("|        UBAH JADWAL DOKTER       |");
      System.out.println("===================================");
      int tempInput = 0;
      String tempPoli = "";
      showPoli(string2dInitialized);
      System.out.print("Masukkan Dokter Poli Yang jadwal ingin diubah : ");
      tempInput = App.sc.nextInt();

      App.Enter(1);
      System.out.printf("Dokter Poli %s : \n", tempPoli);
      for (int i = 0; i < string3dInitialized[0][tempInput - 1].length; i++) {
        System.out.printf("%d. %s [Jadwal : %s]\n", (i + 1), string3dInitialized[0][tempInput - 1][i],
            string3dInitialized[1][tempInput - 1][i]);
      }

      System.out.println();
      System.out.print("Pilih Dokter [angka] : ");
      int getDokter = App.sc.nextInt();
      System.out.print("Masukkan jadwal baru [Format : (00:00)] : ");
      App.sc.nextLine();
      String getSchedule = App.sc.nextLine();

      App.Enter(1);
      System.out.print("Apakah Anda benar benar ingin mengganti jadwal : ");
      char confirmChange = App.sc.next().charAt(0);
      if (confirmChange == 'y') {
        string3dInitialized[1][tempInput - 1][getDokter - 1] = getSchedule;
      }

      changeDokterCondition = App.getConfirmation("mengganti jadwal lagi? : ");
    }
  }

  private static void changeDokter(String[][] string2dInitialized, String[][][] string3dInitialized) {
    boolean changeDokterCondition = true;
    while (changeDokterCondition) {
      App.Enter(1);
      System.out.println("============================");
      System.out.println("|        UBAH DOKTER       |");
      System.out.println("============================");
      int tempInput = 0;
      String tempPoli = "";
      showPoli(string2dInitialized);
      System.out.print("Masukkan Dokter Poli Yang ingin diubah : ");
      tempInput = App.sc.nextInt();

      App.Enter(1);
      System.out.printf("Dokter Poli %s : \n", tempPoli);
      for (int i = 0; i < string3dInitialized[0][tempInput - 1].length; i++) {
        System.out.printf("%d. %s\n", (i + 1), string3dInitialized[0][tempInput - 1][i]);
      }

      System.out.println();
      System.out.print("Pilih Dokter [angka] : ");
      int getDokter = App.sc.nextInt();
      System.out.print("Masukkan Dokter Baru : ");
      App.sc.nextLine();
      String tempDokter = App.sc.nextLine();

      System.out.print("Yakin ingin mengganti Dokter [y/n] : ");
      char confirmChangeDokter = App.sc.next().charAt(0);
      if (confirmChangeDokter == 'y') {
        string3dInitialized[0][tempInput - 1][getDokter - 1] = tempDokter;
      }

      changeDokterCondition = App.getConfirmation("mengubah Dokter : ");
    }
  }

  private static void changeSlotDokter(int[][] kuotaDokter, String[][] string2dInitialized,
      String[][][] string3dInitialized) {

    int tempInput = 0;
    String tempPoli = "";
    App.Enter(1);
    showPoli(string2dInitialized);
    System.out.print("Masukkan Dokter Poli Yang ingin diubah : ");
    tempInput = App.sc.nextInt();
    tempPoli = string2dInitialized[1][tempInput - 1];

    App.Enter(1);
    System.out.printf("Dokter Poli %s : \n", tempPoli);
    for (int i = 0; i < string3dInitialized[0][tempInput - 1].length; i++) {
      System.out.printf("%d. %s => kuota : %d\n", (i + 1), string3dInitialized[0][tempInput - 1][i],
          kuotaDokter[tempInput - 1][i]);
    }

    App.Enter(1);
    System.out.print("Pilih Dokter yang ingin Anda ganti kuota [angka] : ");
    int getDokter = App.sc.nextInt();

    App.Enter(1);
    System.out.println("========================");
    System.out.println("||  UBAH SLOT DOKTER  ||");
    System.out.println("========================");
    System.out.println("1. TAMBAH");
    System.out.println("2. KURANG");
    System.out.println("3. GANTI");
    int getInput = App.sc.nextInt();
    switch (getInput) {
      case 1:
        addSlot(kuotaDokter, tempInput - 1, getDokter - 1);
        break;
      case 2:
        reduceSlot(kuotaDokter, tempInput - 1, getDokter - 1);
        break;
      case 3:
        changeSlot(kuotaDokter, tempInput - 1, getDokter - 1);
        break;
    }
  }

  private static void changeSlot(int[][] kuotaDokter, int i, int j) {
    System.out.println("Ingin mengganti kuota menjadi berapa? : ");
    kuotaDokter[i][j] = App.sc.nextInt();
    System.out.println("Kuota berhasil diganti");
  }

  private static void reduceSlot(int[][] kuotaDokter, int i, int j) {
    System.out.println("Ingin mengurangkan berapa kuota? : ");
    kuotaDokter[i][j] += App.sc.nextInt();
    System.out.println("Kuota berhasil dikurangi");
  }

  private static void addSlot(int[][] kuotaDokter, int i, int j) {
    System.out.println("Ingin menambahkan berapa kuota? : ");
    kuotaDokter[i][j] += App.sc.nextInt();
    System.out.println("Kuota berhasil ditambahkan ^_^ ");
  }

  private static void checkPemesananPasien(String[] infoBookingUser1, int[] infoBookingUser2) {
    App.Enter(1);
    System.out.println("==========================");
    System.out.println("||    PASIEN BOOKING    ||");
    System.out.println("==========================");
    for (int i = 0; i < infoBookingUser1.length; i++) {
      if (infoBookingUser1[i] != null) {
        System.out.printf("%d. %s [Tanggal : %d]\n", (i + 1), infoBookingUser1[i], infoBookingUser2[i]);
      } else {
        System.out.println("Belum Ada Pemesan");
        break;
      }
    }
  }

  private static void checkInformationPatient(String[][] dataPatient) {
    App.Enter(1);
    System.out.println("============================");
    System.out.println("||       DATA PASIEN      ||");
    System.out.println("============================");
    System.out
        .println("-----------------------------------------------------------------------------------------------");
    System.out.println("Nama\t\t| NIK\t\t| Tanggal Lahir\t| Tempat Lahir\t| Alamat\t\t| Kontak");
    System.out
        .println("-----------------------------------------------------------------------------------------------");
    for (String[] i : dataPatient) {
      for (String j : i) {
        if (j == null) {
          break;
        } else {
          System.out.printf("%s\t\t| ", j);
        }
      }
      if (i[0] == null) {
        System.out
            .println("-----------------------------------------------------------------------------------------------");
        break;
      } else {
        System.out.println();
      }
    }
  }

  private static void checkAccPatient(String[][] account) {
    System.out.println("============================");
    System.out.println("||       AKUN PASIEN      ||");
    System.out.println("============================");
    System.out.println("Akun\t     | Password\t    |");
    for (int i = 0; i < account.length; i++) {
      for (int j = 0; j < account[0].length; j++) {
        if (account[i][0] != null) {
          System.out.print(account[i][j] + "\t     | ");
        } else {
          break;
        }
      }
      if (account[i][0] == null) {
        break;
      } else {
        System.out.println();
      }
    }
  }

  private static void showPoli(String[][] string2dInitialized) {
    for (int j = 0; j < string2dInitialized[1].length; j++) {
      System.out.printf("%d. %s\n", (j + 1), string2dInitialized[1][j]);
    }
  }
}
