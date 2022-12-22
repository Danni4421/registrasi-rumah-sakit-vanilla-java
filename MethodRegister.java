package FINAL;

public class MethodRegister {

  // FIRST TIME REGISTER TO THIS METHOD
  public static void Register(String[][] dataPatient, String[][] account) {
    App.Enter(1);
    System.out.println("1. Buat Akun");
    System.out.print("=> ");
    int choosing = App.sc.nextInt();
    App.Enter(1);

    if (choosing == 1) {
      createAcc(account, dataPatient);
    }
  }

  // KEMUDIAN SETELAH DI REGISTER AKAN MENUJU PADA CREATE ACC UNTUK MEMBUAT AKUN
  private static void createAcc(String[][] account, String[][] dataPatient) {
    String accId = "", accPass = "";
    boolean conditionRegister = false;
    for (int j = 0; j < account[0].length; j++) {
      if (account[App.userIndexAcc][j] == null) { // CHECK APABILA NULL MAKA AKAN MASUK KE BUAT ID
        if (j == 0) {
          System.out.println("Buat ID Anda [Masukkan 8 Karakter]");
          System.out.print("=> ");
          accId = App.sc.next();
          for (int k = 0; k < account.length; k++) {
            if (accId.equals(account[k][0])) { // APABILA ADA ID YANG SAMA MAKA AKAN MENGULANG UNTUK MEMBUAT ID LAGI
              System.out.println("ID Yang anda masukkan sudah digunakan");
              createAcc(account, dataPatient); /*
                                                * DENGAN PEMANGGILAN METHOD CREATEACC UNTUK MELAKUKAN PEMBUATAN ID
                                                * ULANG
                                                */
              break;
            } else {
              conditionRegister = true;// JIKA TRUE MAKA AKAN DILAKUKAN REGISTRASI UNTUK DATA PRIBADI PASIEN
              continue;
            }
          }
          continue;
        } else if (j == 1) {
          account[App.userIndexAcc][0] = accId; // MEMASUKKAN ID KEDALAM AKUN PASIEN
          System.out.println("Masukkan password yang ingin ada buat");
          System.out.print("=> ");
          accPass = App.sc.next(); // MENGAMBIL INPUT PASSWORD USER
          account[App.userIndexAcc][1] = accPass;
          conditionRegister = true; // JIKA TRUE MAKA AKAN DILAKUKAN REGISTRASI UNTUK DATA PRIBADI PASIEN
        }
      } else {
        App.userIndexAcc++;
        continue;
      }
      break;
    }

    if (conditionRegister) { // Jika berhasil membuat id dan pw maka langsung otw input data diri
      App.Enter(1);
      System.out.println("Mohon Untuk Melengkapi Data Diri ^_^ ");
      register(dataPatient);
    }
  }

  // REGISTER MEMASUKKAN DATA DIRI INFORMASI TERKAIT PASIEN
  private static void register(String[][] dataPatient) {
    for (int i = App.userIndexAcc; i < dataPatient.length; i++) {
      for (int j = 0; j < dataPatient[0].length; j++) {
        if (dataPatient[i][j] == null) {
          switch (j) {
            case 0:
              System.out.println("Masukkan Nama");
              System.out.print("=> ");
              App.sc.nextLine();
              dataPatient[i][j] = App.sc.nextLine();
              break;

            case 1:
              boolean nik = true;
              String NIK = "";
              do {
                System.out.println("Masukkan NIK");
                System.out.print("=> ");
                NIK = App.sc.next();
                nik = checkNIK(NIK, dataPatient);
                if (nik) {
                  System.out.println("Maaf NIK Anda mungkin salah!!");
                } else {
                  dataPatient[i][j] = NIK;
                }
              } while (nik);
              break;

            case 2:
              System.out.println("Tanggal Lahir (Format[dd/mm/yy])");
              System.out.print("=> ");
              dataPatient[i][j] = App.sc.next();
              break;

            case 3:
              System.out.println("Masukkan Tempat Lahir");
              System.out.print("=> ");
              dataPatient[i][j] = App.sc.next();
              break;

            case 4:
              System.out.println("Masukkan Alamat");
              System.out.print("=> ");
              App.sc.nextLine();
              dataPatient[i][j] = App.sc.nextLine();
              break;

            case 5:
              System.out.println("Nomor Kontak");
              System.out.print("=> ");
              dataPatient[i][j] = App.sc.next();
              break;
          }
        }
        App.Enter(1);
      }
      App.userIndexAcc++;
      if (dataPatient[i][0] != null) {
        break;
      }
    }
    System.out.println("Terima kasih telah melengkapi data Anda");
  }

  private static boolean checkNIK(String NIK, String[][] dataPatient) {
    boolean resultNIK = false;

    for (int i = 0; i < dataPatient.length; i++) {
      if (NIK.equals(dataPatient[i][1])) {
        resultNIK = true;
        break;
      } else if (!NIK.equals(dataPatient[i][1])) {
        resultNIK = false;
      }
    }

    return resultNIK;
  }
}
