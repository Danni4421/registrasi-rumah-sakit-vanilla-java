package FINAL;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MethodLogin {

  static int indexOfBookingDate = 0;

  public static boolean checkLogin(String[][] acc) {
    App.Enter(1);
    // App.menuToogle("HALAMAN LOGIN");
    System.out.println("===============================");
    System.out.println("||         LOGIN USER        ||");
    System.out.println("===============================");
    System.out.print("MASUKKAN INPUT ID :");
    String getInputID = App.sc.next();
    System.out.print("MASUKKAN INPUT PASSWORD : ");
    App.sc.nextLine();
    String getInputPass = App.sc.nextLine();

    boolean result = loginValidation(getInputID, getInputPass, acc);

    return result;

  }

  private static boolean loginValidation(String getID, String getPass, String[][] acc) {
    boolean resultID = false, resultPass = false, hasil = false;

    for (int i = 0; i < acc.length; i++) {
      if (acc[i][0] != null) {
        if (acc[i][0].equals(getID)) {
          resultID = true;
        }

        if (acc[i][1].equals(getPass)) {
          resultPass = true;
          App.getDataLogin = i;
          break;
        }

        continue;
      } else if (acc[i][0] == null) {
        System.out.println("Maaf Data Tidak ada!, Mohon Untuk Registrasi Terlebih Dahulu ^_^ ");
        hasil = false;
        break;
      }

    }

    if (resultID && resultPass) {
      hasil = true;
    } else {
      hasil = false;
    }

    return hasil;
  }

  public static void LoginTrue(String[][] acc, String[][] dataPatient, String[][] bookingData,
      boolean[][] jadwalDokter, int[][] kuotaDokter, double[] biayaAwal, char[][] randomCodePreparation,
      boolean[][] bookingNPaidDone, String[] paymentCode, String[][] string2dInitialized,
      String[][][] string3dInitialized, double[][] saldoJaminanNBillPatient, int[][] array2dDate,
      String[] infoBookingUser1, int[] infoBookingUser2, int[] kuotaDokterUmum, int[][] nomorKuota) {

    boolean confirm = true;
    // if (App.login) {
    do {
      App.Enter(1);
      System.out.println("=========================================");
      System.out.println("||              Menu Pasien            ||");
      System.out.println("======= Rumah Sakit Fathma Medika  ======");
      System.out.println("-----------------------------------------");
      System.out.println();
      System.out.println("=========================================");
      System.out.println("||    1. Booking                       ||");
      System.out.println("=========================================");
      System.out.println("||    2. Edit Data Diri                ||");
      System.out.println("=========================================");
      System.out.println("||    3. Lihat Jadwal Doktor           ||");
      System.out.println("=========================================");
      System.out.println("||    4. Check Tanggal Pemesan Lain    ||");
      System.out.println("=========================================");
      System.out.println("||    5. Logout                        ||");
      System.out.println("=========================================");
      System.out.print("=> ");
      int i = App.sc.nextInt();

      switch (i) {
        case 1:
          bookingPage(acc, dataPatient, bookingData, jadwalDokter, kuotaDokter, biayaAwal, randomCodePreparation,
              paymentCode, string2dInitialized, string3dInitialized, saldoJaminanNBillPatient, bookingNPaidDone,
              array2dDate, infoBookingUser1, infoBookingUser2, kuotaDokterUmum, nomorKuota);
          break;
        case 2:
          editInformation(dataPatient, array2dDate, bookingNPaidDone, infoBookingUser1, infoBookingUser2);
          break;
        case 3:
          App.getJadwalDoktor(jadwalDokter, string3dInitialized, string2dInitialized, kuotaDokter);
          break;
        case 4:
          checkPasienBooking(array2dDate, bookingData, dataPatient, bookingNPaidDone, infoBookingUser1,
              infoBookingUser2);
          break;
        case 5:
          confirm = logOut();
          break;
      }

      if (confirm != false) {
        confirm = App.getConfirmation("Ke Halaman Booking");
      }
    } while (confirm);
  }

  private static void checkUserBooking(int[][] array2dDate, String[][] bookingData, String[][] dataPatient,
      boolean[][] bookingNPaidDone, String[] infoBookingUser1, int[] infoBookingUser2) {
    App.Enter(1);
    int temp = array2dDate[0][0];
    if (temp == 0) {
      System.out.println("Belum ada User yang melakukan Booking silahkan Booking sekarang");
    } else {
      for (int i = 0; i < infoBookingUser1.length; i++) {
        if (infoBookingUser1[i] == null) {
          break;
        } else {
          if (infoBookingUser1[i + 1] == null) {
            break;
          } else {
            int tempArrDate = 0;
            String tempArrName = "";
            int tempDay = 0;
            int tempMonth = 0;
            int tempYear = 0;

            for (int k = 0; k < infoBookingUser1.length; k++) {
              for (int j = k + 1; j < infoBookingUser1.length; j++) {
                if (infoBookingUser2[k] > infoBookingUser2[j]) {
                  if (infoBookingUser2[j] == 0) {
                    break;
                  } else {
                    tempArrName = infoBookingUser1[k];
                    tempArrDate = infoBookingUser2[k];
                    tempDay = array2dDate[k][0];
                    tempMonth = array2dDate[k][1];
                    tempYear = array2dDate[k][2];
                    infoBookingUser1[k] = infoBookingUser1[j];
                    infoBookingUser2[k] = infoBookingUser2[j];
                    array2dDate[k][0] = array2dDate[j][0];
                    array2dDate[k][1] = array2dDate[j][1];
                    array2dDate[k][2] = array2dDate[j][2];
                    infoBookingUser1[j] = tempArrName;
                    infoBookingUser2[j] = tempArrDate;
                    array2dDate[j][0] = tempDay;
                    array2dDate[j][1] = tempMonth;
                    array2dDate[j][2] = tempYear;
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  private static void checkPasienBooking(int[][] array2dDate, String[][] bookingData, String[][] dataPatient,
      boolean[][] bookingNPaidDone, String[] infoBookingUser1, int[] infoBookingUser2) {
    System.out.println("================================");
    System.out.println("|| PESANAN PASIEN RUMAH SAKIT ||");
    System.out.println("================================");
    checkUserBooking(array2dDate, bookingData, dataPatient, bookingNPaidDone, infoBookingUser1, infoBookingUser2);
    for (int i = 0; i < infoBookingUser1.length; i++) {
      if (infoBookingUser1[i] == null) {
        break;
      } else {
        System.out.printf("%d. %s => ", (i + 1), infoBookingUser1[i]);
        for (int j = 0; j < array2dDate[0].length; j++) {
          System.out.print(array2dDate[i][j] + "/");
        }
        System.out.println();
      }
    }
  }

  private static void bookingPage(String[][] acc, String[][] dataPatient, String[][] bookingData,
      boolean[][] jadwalDokter, int[][] kuotaDokter, double[] biayaAwal, char[][] randomCodePreparation,
      String[] paymentCode, String[][] string2dInitialized, String[][][] string3dInitialized,
      double[][] saldoJaminanNBillPatient, boolean[][] bookingNPaidDone, int[][] array2dDate,
      String[] infoBookingUser1, int[] infoBookingUser2, int[] kuotaDokterUmum, int[][] nomorKuota) {

    int getJaminan = 0;
    int dokterIndex = 0;
    // IF USER HAVE BOOKING ->
    if (bookingNPaidDone[0][App.getDataLogin]) {
      System.out.println("------------------------------");
      System.out.println("|     Anda Sudah Booking     |");
      System.out.println("------------------------------");
      System.out.println("1. Check Pemesanan");
      System.out.println("2. Note Pembayaran");
      System.out.println("3. Pembayaran");
      if (!bookingData[App.getDataLogin][3].equals("None")) {
        System.out.println("4. Check Jaminan Pribadi");
      }

      System.out.print("=> ");
      int getInputAfterBooking = App.sc.nextInt();
      switch (getInputAfterBooking) {
        case 1:
          noteBooking(dataPatient, bookingData, bookingNPaidDone[0], string3dInitialized[1], biayaAwal,
              saldoJaminanNBillPatient);
          break;
        case 2:
          getPayment(bookingData, bookingNPaidDone[1]);
          break;
        case 3:
          pembayaran(bookingData, string3dInitialized[2], bookingNPaidDone);
          break;
        case 4:
          checkJaminan(dataPatient, bookingData, biayaAwal, saldoJaminanNBillPatient, string2dInitialized,
              bookingNPaidDone);
          break;
      }
    } else { // IF USER NOT LOGIN YET

      boolean resultBooking = false, getDate = false;
      int indexPelayanan = 0;

      do {
        App.Enter(1);
        // INPUT UNTUK MENENTUKAN USER INGIN MELAKUKAN BOOKING ATAU TIDAK
        System.out.println("Ingin melakukan Booking? [y/n]");
        System.out.print("=> ");
        char inputBooking = App.sc.next().charAt(0);
        if (inputBooking == 'y') { // Apabila 'y' maka aka dilakukan input booking
          App.Enter(1);
          for (int i = 0; i < bookingData[0].length; i++) { // melakukan looping sesuai index dari element bookingData
            switch (i) {
              case 0:
                System.out.println("<<<<< PILIH PELAYANAN >>>>>");
                System.out.println("1. Pemeriksaan");
                System.out.println("2. Rawat Inap");
                System.out.println("3. Rawat Jalan");
                System.out.print("=> ");
                indexPelayanan = App.sc.nextInt();
                resultBooking = getPemeriksaan(indexPelayanan, bookingData);
                if (resultBooking) {
                  bookingNPaidDone[0][App.getDataLogin] = true;
                } else {
                  System.out.println("Maaf, Pelayanan Belum Tersedia");
                  bookingNPaidDone[0][App.getDataLogin] = false;
                  resetDataBooking(bookingData, bookingNPaidDone[0], App.getDataLogin);
                }
                break;
              // CASE UNTUK MEMILIKI POLI PADA RUMAH SAKIT
              case 1:
                App.Enter(1);
                System.out.println("<<<<< PILIH POLI >>>>>");
                boolean jenisPoli = App.showPoli(string2dInitialized[1], indexPelayanan);
                if (jenisPoli) {
                  System.out.print("=> ");
                  App.takeInputPoli = App.sc.nextInt();
                  resultBooking = getPoli(string2dInitialized[1], bookingData, App.takeInputPoli, i, indexPelayanan);
                } else {
                  bookingData[App.getDataLogin][1] = "Umum";
                  resultBooking = true;
                }

                if (resultBooking) {
                  bookingNPaidDone[0][App.getDataLogin] = true;
                } else {
                  System.out.println("Maaf, Poli Tidak Tersedia");
                  bookingNPaidDone[0][App.getDataLogin] = false;
                  resetDataBooking(bookingData, bookingNPaidDone[0], App.getDataLogin);
                }
                break;

              // CASE UNTUK MEMILIH DOKTER SESUAI DENGAN POLI
              case 2:
                App.Enter(1);
                System.out.println("<<<<< PILIH DOKTER >>>>>");
                showDokter(string2dInitialized[1], string3dInitialized[0], string2dInitialized[2], App.takeInputPoli,
                    indexPelayanan);
                System.out.print("=> ");
                dokterIndex = App.sc.nextInt();
                resultBooking = setDokter(dokterIndex, string3dInitialized[0], bookingData, i, kuotaDokter,
                    indexPelayanan, string2dInitialized[2], kuotaDokterUmum);
                if (resultBooking) {
                  bookingNPaidDone[0][App.getDataLogin] = true;
                } else {
                  System.out.println("Gagal Memilih Dokter");
                  bookingNPaidDone[0][App.getDataLogin] = false;
                  resetDataBooking(bookingData, bookingNPaidDone[0], App.getDataLogin);
                }
                break;

              // CASE UNTUK MENGAMBIL INPUT DARI USER ATAU PASIEN APAKAH MEMILIKI JAMINAN
              // SOSIAL
              // SEHINGGA DAPAT DIGUNAKAN UNTUK MENENTUKAN COVER PEMBIAYAAN.
              case 3:
                App.Enter(1);
                System.out.println("<<<<< PILIH JAMINAN >>>>>");
                showJaminan(string2dInitialized[0]);
                System.out.print("=> ");
                getJaminan = App.sc.nextInt();
                resultBooking = setJaminan(string2dInitialized[0], bookingData, getJaminan, i, getJaminan);
                if (resultBooking) {
                  bookingNPaidDone[0][App.getDataLogin] = true;
                } else {
                  System.out.println("Maaf, Jaminan yang Anda pilih tidak ada");
                  bookingNPaidDone[0][App.getDataLogin] = false;
                  resetDataBooking(bookingData, bookingNPaidDone[0], App.getDataLogin);
                }
                break;

              // CASE UNTUK MELAKUKAN INPUT SALDO JAMINAN DARI USER YANG KEMUDIAN DIGUNAKAN
              // UNTUK COVER BIAYA PENANGANAN
              case 4:
                App.Enter(1);
                inputSaldoJaminan(bookingData, i, string2dInitialized[0], getJaminan);
                // getIndexJaminanSaldo = i;
                break;

              // CASE UNTUK MENGAMBIL INPUT DARI TANGGAL MELAKUKAN BOOKING
              case 5:
                getDate = dayBooking(bookingData, bookingNPaidDone[0], i, array2dDate, infoBookingUser1,
                    infoBookingUser2, dataPatient);
                if (resultBooking) {
                  bookingNPaidDone[0][App.getDataLogin] = true;
                } else {
                  System.out.println("Maaf, Mungkin Anda memasukkan hari lebih dari seminggu / terjadi kesalahan");
                  bookingNPaidDone[0][App.getDataLogin] = false;
                  resetDataBooking(bookingData, bookingNPaidDone[0], App.getDataLogin);
                }
                break;

              // CASE UNTUK MELAKUKAN INPUT TERHADAP TANGGAL WAKTU KITA BOOKING
              case 6:
                resultBooking = getDate;
                if (resultBooking) {
                  bookingNPaidDone[0][App.getDataLogin] = true;
                } else {
                  System.out.println("Maaf, Ada kesalahan dalam input data date");
                  bookingNPaidDone[0][App.getDataLogin] = false;
                  resetDataBooking(bookingData, bookingNPaidDone[0], App.getDataLogin);
                }
                break;

              // CASE UNTUK MENENTUKAN BIAYA PENANGANAN POLI
              case 7:
                resultBooking = getPoliBill(bookingData, App.takeInputPoli, biayaAwal, i, saldoJaminanNBillPatient,
                    bookingNPaidDone, indexPelayanan);
                if (resultBooking) {
                  bookingNPaidDone[0][App.getDataLogin] = true;
                } else {
                  System.out.println("Maaf, Ada kesalahan dalam melakukan pengambilan data bill");
                  bookingNPaidDone[0][App.getDataLogin] = false;
                  resetDataBooking(bookingData, bookingNPaidDone[0], App.getDataLogin);
                }
                break;

              // CASE UNTUK MENGAMBIL RANDOM CODE UNTUK DIGUNAKAN SEBAGAI PAYMENT CODE
              case 8:
                String randomCode = App.getRandomCode(randomCodePreparation, 8);
                bookingData[App.getDataLogin][i] = randomCode;
                if (bookingData[App.getDataLogin][i] != null) {
                  App.Enter(1);
                  System.out.println("Anda telah mendapatkan kode pembayaran");
                  App.Enter(1);

                  App.paymentIndex = i;
                  resultBooking = true;
                  bookingNPaidDone[0][App.getDataLogin] = true;
                } else {
                  System.out.println("Maaf, Tidak dapat melakukan generating kode pembayaran");
                  resultBooking = false;
                  bookingNPaidDone[0][App.getDataLogin] = false;
                  resetDataBooking(bookingData, bookingNPaidDone[0], App.getDataLogin);
                }
                break;

              // CASE UNTUK MENENTUKAN NOMOR ANTREAN
              case 9:
                getAntrean(bookingData, nomorKuota, i, kuotaDokter, kuotaDokterUmum, indexPelayanan, App.takeInputPoli,
                    dokterIndex);
                break;

              default:
                System.out.println("Terima Kasih telah melakukan booking");
                resultBooking = false;
                break;
            }
            if (resultBooking) {
              continue;
            } else {
              break;
            }
          }
        } else if (inputBooking == 'n') { // Apabila 'n' dan selainnya maka tidak jadi melakukan booking
          break;
        } else {
          break;
        }

      } while (resultBooking == false);
    }

  }

  private static void checkJaminan(String[][] dataPatient, String[][] bookingData,
      double[] biayaAwal, double[][] saldoJaminanNBillPatient, String[][] string2dInitialized,
      boolean[][] bookingNPaidDone) {
    boolean condition = false;
    do {
      System.out.println("=================================");
      System.out.println("||       JAMINAN PASIEN        ||");
      System.out.println("=================================");
      System.out.println("1. Cek Saldo");
      System.out.println("2. Ganti Jaminan");
      System.out.println("3. Tambah Saldo");
      System.out.print("=> ");
      int inputMenu = App.sc.nextInt();

      switch (inputMenu) {
        case 1:
          JaminanPatient.checkSaldoPatient(dataPatient, bookingData, saldoJaminanNBillPatient);
          break;
        case 2:
          JaminanPatient.changeJaminan(dataPatient, bookingData, string2dInitialized);
          break;
        case 3:
          double saldoJaminanNew = JaminanPatient.addSaldo(saldoJaminanNBillPatient);
          JaminanPatient.updateBillPatient(saldoJaminanNew, bookingData, saldoJaminanNBillPatient, bookingNPaidDone);
          break;
      }
    } while (condition);

  }

  private static void getAntrean(String[][] bookingData, int[][] nomorKuota, int i, int[][] kuotaDokter,
      int[] kuotaDokterUmum, int indexPelayanan, int takeInputPoli, int dokterIndex) {
    String getNomorKuota = "";
    char kuotaUmum = 'U';
    char poliChar[] = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'
    };
    if (indexPelayanan > 1) {
      if (kuotaDokter[App.takeInputPoli - 1][dokterIndex - 1] != 0) {
        getNomorKuota = Integer.toString(nomorKuota[App.takeInputPoli - 1][dokterIndex - 1]);
        char getPoliChar = poliChar[App.takeInputPoli - 1];
        bookingData[App.getDataLogin][i] = getNomorKuota + getPoliChar;
        System.out.println("Anda mendapatkan nomor Antrean " + bookingData[App.getDataLogin][i]);
        nomorKuota[App.takeInputPoli - 1][dokterIndex - 1]++;
        kuotaDokter[App.takeInputPoli - 1][dokterIndex - 1]--;
      } else {
        System.out.println("Nomor Antrean Habis");
      }
    } else {
      if (kuotaDokterUmum[dokterIndex - 1] != 0) {
        getNomorKuota = Integer.toString(App.nomorKuotaDokterUmum[dokterIndex - 1]);
        bookingData[App.getDataLogin][i] = getNomorKuota + kuotaUmum;
        System.out.println("Anda mendapatkan nomor Antrean Poli Umum " + bookingData[App.getDataLogin][i]);
        App.nomorKuotaDokterUmum[dokterIndex - 1]++;
      } else {
        System.out.println("Nomor Antrean Habis");
      }
    }
  }

  private static boolean getPemeriksaan(int indexPelayanan, String[][] bookingData) {
    boolean result = false;
    switch (indexPelayanan) {
      case 1:
        bookingData[App.getDataLogin][0] = "Pemeriksaan";
        result = true;
        break;
      case 2:
        bookingData[App.getDataLogin][0] = "Rawat Inap";
        result = true;
        break;
      case 3:
        bookingData[App.getDataLogin][0] = "Rawat Jalan";
        result = true;
        break;
    }
    return result;
  }

  // METHOD UNTUK MENGAMBIL BILL DARI SETIAP POLI PILIHAN USER ATAU PASIEN KEITKA
  // BOOKING
  private static boolean getPoliBill(String[][] bookingData, int takeInputPoli, double[] biayaAwal, int i,
      double[][] saldoJaminanNBillPatient, boolean[][] bookingNPaidDone, int indexPelayanan) {
    // CONDITION
    boolean resultGetPoliBill = false;

    double tempBill = 0;
    double saldoJaminan = 0;

    if (indexPelayanan > 1) {
      tempBill = biayaAwal[takeInputPoli - 1];
    } else {
      tempBill = 50.000;
    }

    if (!bookingData[App.getDataLogin][4].equals("-")) {
      saldoJaminan = Double.parseDouble(bookingData[App.getDataLogin][4]);
      System.out.println("Ini saldo saat ini" + saldoJaminan);
      double newTempBill = tempBill - saldoJaminan;
      double sisaSaldoJaminan = saldoJaminan - tempBill;

      if (sisaSaldoJaminan <= 0) {
        sisaSaldoJaminan = 0;
      }

      tempBill -= saldoJaminan;

      // cek tempbill apakah lebih kecil dari pada 0
      if (tempBill <= 0) {
        bookingData[App.getDataLogin][i] = "Lunas";
        bookingNPaidDone[1][App.getDataLogin] = true;
      } else {
        bookingData[App.getDataLogin][i] = Double.toString(newTempBill);
      }

      if (sisaSaldoJaminan < 0) {
        sisaSaldoJaminan = 0;
      }

      saldoJaminanNBillPatient[1][App.getDataLogin] = newTempBill;
      saldoJaminanNBillPatient[0][App.getDataLogin] = sisaSaldoJaminan;

      resultGetPoliBill = true;
    } else {
      System.out.println("");
      bookingData[App.getDataLogin][i] = Double.toString(tempBill);
      resultGetPoliBill = true;
    }

    return resultGetPoliBill;
  }

  private static boolean dayBooking(String[][] bookingData, boolean[] bs, int i, int[][] array2dDate,
      String[] infoBookingUser1, int[] infoBookingUser2, String[][] dataPatient) {
    boolean resultDataDate = false;
    Date date = new Date();
    SimpleDateFormat dayDate = new SimpleDateFormat("dd");
    SimpleDateFormat monthDate = new SimpleDateFormat("MM");
    SimpleDateFormat yearDate = new SimpleDateFormat("y");

    int dayNow = Integer.parseInt(dayDate.format(date));
    int monthNow = Integer.parseInt(monthDate.format(date));
    int yearNow = Integer.parseInt(yearDate.format(date));

    String dateNow = Integer.toString(dayNow) + " | " + Integer.toString(monthNow) + " | " + Integer.toString(yearNow);
    App.Enter(1);
    System.out.println("Sekarang Tanggal : " + dateNow);
    bookingData[App.getDataLogin][i] = dateNow;

    if (bookingData[App.getDataLogin][i] == null) {
      resultDataDate = false;
    } else {
      resultDataDate = getInputDateBooking(bookingData, (i + 1), dayNow, monthNow, yearNow, array2dDate,
          infoBookingUser1, infoBookingUser2, dataPatient);
    }

    return resultDataDate;
  }

  private static boolean getInputDateBooking(String[][] bookingData, int i, int dayNow, int monthNow, int yearNow,
      int[][] array2dDate, String[] infoBookingUser1, int[] infoBookingUser2, String[][] dataPatient) {
    boolean resultInputBooking = false;
    App.Enter(1);
    System.out.println("<<<<< MASUKKAN TANGGAL BOOKING >>>>>");
    System.out.println("Noted : Dapat memesan booking maksimum 7 hari dari hari pemesanan!");
    // SOUT TAHUN
    System.out.println("Tahun => " + yearNow);

    // SOUT BULAN
    System.out.println("Bulan => " + monthNow);

    // MAMASUKKAN HARI
    System.out.print("Masukkan Hari => ");
    int day = App.sc.nextInt();
    // JIKA HARI INPUT LEBIH KECIL MAKA TIDAK AKAN MASUK PADA KONDISI DIBAWAH
    if (day >= dayNow && day <= dayNow + 7) {
      bookingData[App.getDataLogin][i] = Integer.toString(day) + " | " + monthNow + " | "
          + yearNow;

      if (array2dDate[indexOfBookingDate][0] == 0) {
        for (int k = 0; k < array2dDate[0].length; k++) {
          switch (k) {
            case 0:
              array2dDate[indexOfBookingDate][k] = day;
              break;
            case 1:
              array2dDate[indexOfBookingDate][k] = monthNow;
              break;
            case 2:
              array2dDate[indexOfBookingDate][k] = yearNow;
              break;
          }
          infoBookingUser1[indexOfBookingDate] = dataPatient[App.getDataLogin][0];
          infoBookingUser2[indexOfBookingDate] = day;
        }
      }

      indexOfBookingDate++;
      resultInputBooking = true;
    } else

    {
      resultInputBooking = false;
    }

    return resultInputBooking;
  }

  // METHOD UNTUK MELAKUKAN INPUT DARI SALDO USER ATAU PASIEN
  private static boolean inputSaldoJaminan(String[][] bookingData, int i, String[] jaminan, int getJaminan) {

    if (getJaminan == jaminan.length) {
      System.out.println("Tidak Memakai Jaminan");
      bookingData[App.getDataLogin][i] = "-";
    } else {
      System.out.println("<<<<< MASUKKAN SALDO >>>>>");
      double saldoUser = App.sc.nextDouble();
      bookingData[App.getDataLogin][i] = Double.toString(saldoUser);
    }

    return true;

  }

  // METHOD UNTUK MERESET DATA BOOKING YANG TERJADI APABILA BOOKING GAGAL
  // DILAKUKAN
  private static void resetDataBooking(String[][] bookingData, boolean[] bookingNPaidDone, int takeInputPoli) {
    for (int j = 0; j < bookingData[App.getDataLogin].length; j++) {
      if (bookingData[App.getDataLogin][j] != null) {
        bookingData[App.getDataLogin][j] = null;
        bookingNPaidDone[App.getDataLogin] = false;
      } else {
        continue;
      }
    }
    bookingNPaidDone[App.getDataLogin] = false;
  }

  // MENGAMBIL POLI SESUAI INPUT USER
  private static boolean getPoli(String[] poli, String[][] bookingData, int getInputPoli, int i, int indexPelayanan) {
    boolean result = true;

    bookingData[App.getDataLogin][1] = poli[getInputPoli - 1];
    if (bookingData[App.getDataLogin][1] == null) {
      result = false;
    }

    return result;
  }

  // MENGAMBIL DOKTER DARI SETIAP POLI YANG DIPILIH
  private static void showDokter(String[] poli, String[][] doktorSp, String[] string2dInitialized, int getInputPoli,
      int indexPelayanan) {
    if (indexPelayanan > 1) {
      System.out.println("Poli " + poli[getInputPoli - 1]);
      for (int j = 0; j < doktorSp[getInputPoli - 1].length; j++) {
        System.out.printf("%d. Dokter %s\n", (j + 1), doktorSp[getInputPoli - 1][j]);
      }
    } else {
      System.out.println("Poli Umum");
      for (int j = 0; j < string2dInitialized.length; j++) {
        if (string2dInitialized[j] == null) {
          break;
        } else {
          System.out.printf("%d. Dokter %s\n", (j + 1), string2dInitialized[j]);
        }
      }
    }
  }

  // UNTUK MENENTUKAN DOKTER YANG DIAMBIL SESAUAI DENGAN INPUT USER
  private static boolean setDokter(int getDokter, String[][] doktor, String[][] bookingData, int i,
      int[][] kuotaDokter, int indexPelayanan, String[] string2dInitialized, int[] kuotaDokterUmum) {
    boolean resultSetDokter = false;

    if (indexPelayanan > 1) {
      bookingData[App.getDataLogin][2] = doktor[App.takeInputPoli - 1][getDokter - 1];
      if (kuotaDokter[App.takeInputPoli - 1][getDokter - 1] == 0) {
        System.out.println("Kuota Dokter Habis");
        resultSetDokter = false;
      } else {
        resultSetDokter = true;
      }
    } else {
      if (kuotaDokterUmum[getDokter - 1] == 0) {
        resultSetDokter = false;
        System.out.println("Maaf Kuota telah habis");
      } else {
        bookingData[App.getDataLogin][2] = string2dInitialized[getDokter - 1];
        resultSetDokter = true;
      }
    }

    return resultSetDokter;
  }

  // MENAMPILKAN MACAM MACAM JAMINAN YANG ADA UNTUK DIPILIH OLEH USER
  private static void showJaminan(String[] jaminan) {
    for (int i = 0; i < jaminan.length; i++) {
      System.out.printf("%d. %s\n", (i + 1), jaminan[i]);
    }
  }

  // MENGAMBIL JAMINAN DARI USER APABILA ADA
  private static boolean setJaminan(String[] jaminan, String[][] bookingData, int getJaminan, int i, int getJaminan2) {
    boolean resultJaminan = false;
    bookingData[App.getDataLogin][i] = jaminan[getJaminan - 1];
    if (bookingData[App.getDataLogin][i] != null) {
      resultJaminan = true;
    }

    return resultJaminan;
  }

  // METHOD AFTER BOOKING IS TRUE
  private static void noteBooking(String[][] dataPatient, String[][] bookingData, boolean[] isBooking,
      String[][] jamDokter, double[] biayaAwal, double[][] saldoJaminanNBillPatient) {
    App.Enter(1);
    System.out.println("=====================================================================");
    System.out.println("||                                                                 ||");
    System.out.println("||                          NOTA BOOKING                           ||");
    System.out.println("||                                                                 ||");
    System.out.println("=============== PELAYANAN RUMAH SAKIT FATHMA MEDIKA =================");

    if (isBooking[App.getDataLogin]) {
      System.out.println("-------------------------------------------------------------------");
      for (int k = 0; k < dataPatient[0].length; k++) {
        switch (k) {
          case 0:
            System.out.println("Pasien Atas Nama \t\t: " + dataPatient[App.getDataLogin][k]);
            break;
          case 1:
            System.out.println("Nomor Induk Kependudukan \t: " + dataPatient[App.getDataLogin][k]);
            break;
          case 2:
            System.out.println("Tanggal Lahir \t\t\t: " + dataPatient[App.getDataLogin][k]);
            break;
          case 3:
            System.out.println("Tempat Lahir \t\t\t: " + dataPatient[App.getDataLogin][k]);
            break;
          case 4:
            System.out.println("Alamat \t\t\t\t: " + dataPatient[App.getDataLogin][k]);
            break;
          case 5:
            System.out.println("Nomor Kontak \t\t\t: " + dataPatient[App.getDataLogin][k]);
            break;
        }
      }

      for (int j = 0; j < bookingData[0].length; j++) {
        switch (j) {
          case 0:
            System.out.println("Pelayanan \t\t\t: " + bookingData[App.getDataLogin][j]);
            break;
          case 1:
            System.out.println("Poli \t\t\t\t: " + bookingData[App.getDataLogin][j]);
            break;
          case 2:
            System.out.println("Dokter \t\t\t\t: " + bookingData[App.getDataLogin][j]);
            break;
          case 3:
            System.out.println("Jaminan \t\t\t: " + bookingData[App.getDataLogin][j]);
            break;
          case 4:
            System.out.println("Saldo Jaminan \t\t\t: " + saldoJaminanNBillPatient[0][App.getDataLogin]);
            break;
          case 5:
            System.out.println("Tanggal Booking \t\t: " + bookingData[App.getDataLogin][j]);
            break;
          case 6:
            System.out.println("Tanggal Pemesanan \t\t: " + bookingData[App.getDataLogin][j]);
            break;
          case 7:
            if (bookingData[App.getDataLogin][j].equalsIgnoreCase("Lunas")) {
              System.out.println("Biaya Poli \t\t\t: " + bookingData[App.getDataLogin][j]);
            } else {
              System.out.println("Biaya Poli \t\t\t: " + bookingData[App.getDataLogin][j] + "00,00");
            }
            break;
          case 8:
            System.out.println("Kode Pembayaran \t\t: " + bookingData[App.getDataLogin][j]);
            break;
          case 9:
            System.out.println("Nomor Antrean \t\t\t: " + bookingData[App.getDataLogin][j]);
            break;
        }
      }

    }

  }

  // MENGAMBIL BIAYA YANG TEPAT SESUAI POLI
  private static void getPayment(String[][] bookingData, boolean[] paidDone) {
    App.Enter(1);
    App.menuToogle("BILL PASIEN");
    if (paidDone[App.getDataLogin]) {
      System.out.println("| Sudah LUNAS |");
    } else {
      App.Enter(2);
      System.out.println("=====================================================================");
      System.out.println("||                                                                 ||");
      System.out.println("||                          BILL PASIEN                            ||");
      System.out.println("||                                                                 ||");
      System.out.println("=============== PELAYANAN RUMAH SAKIT FATHMA MEDIKA =================");
      System.out.println("---------------------------------------------------------------------");
      for (int i = 0; i < bookingData[0].length; i++) {
        switch (i) {
          case 0:
            System.out.println("Jenis Pelayanan : " + bookingData[App.getDataLogin][i]);
            break;
          case 1:
            System.out.println("Poli \t\t: " + bookingData[App.getDataLogin][i]);
            break;
          case 2:
            System.out.println("Dokter \t\t: " + bookingData[App.getDataLogin][i]);
            break;
          case 3:
            System.out.println("Jaminan \t: " + bookingData[App.getDataLogin][i]);
            break;
          case 5:
            System.out.println("Tanggal Booking : " + bookingData[App.getDataLogin][i]);
            break;
          case 7:
            System.out.println("BILL \t\t: " + bookingData[App.getDataLogin][i] + "00,00");
            break;
          case 8:
            System.out.println("Kode Bayar \t: " + bookingData[App.getDataLogin][i]);
            break;
        }
      }
    }
  }

  // FUNGSI UNTUK MELAKUKAN PEMBAYARAN SAMPAI LUNAS
  private static void pembayaran(String[][] bookingData, String[][] paymentMethod, boolean[][] bookingNPaidDone) {
    boolean conditionOfPayment = false;
    while (!conditionOfPayment) {
      App.Enter(1);
      System.out.println("<<<<< MASUKKAN KODE PEMBAYARAN >>>>>");
      System.out.print("=> ");
      App.sc.nextLine();
      String getPaymentCode = App.sc.nextLine();
      for (int i = 0; i < bookingData.length; i++) {
        if (getPaymentCode.equalsIgnoreCase(bookingData[i][App.paymentIndex])) {
          App.Enter(1);
          System.out.println("~~ KODE PEMBAYARAN DITEMUKAN ^_^ ~~");
          App.Enter(1);
          conditionOfPayment = true;
          if (bookingNPaidDone[1][i]) {
            conditionOfPayment = true;
            System.out.println("PEMBAYARAN SELESAI");
            break;
          } else {
            double getTarifPembayaran = Double.parseDouble(bookingData[i][App.paymentIndex - 1]);
            if (getTarifPembayaran != 0) {
              System.out.println("<<<<< PROSES PEMBAYARAN >>>>>");
              System.out.println("Pilih Metode Pembayaran : ");
              System.out.println("1. Transfer Bank");
              System.out.println("2. M-Banking");
              System.out.println("3. Dana Digital");
              System.out.println("4. Gerai");
              System.out.print("=> ");
              int getInputPaymentMethod = App.sc.nextInt();

              payBill(bookingNPaidDone, paymentMethod, getInputPaymentMethod, bookingData);
              conditionOfPayment = true;
              break;
            } else {
              System.out.println("Pembayaran Selesai");
              conditionOfPayment = true;
              break;
            }
          }
        } else {
          conditionOfPayment = false;
        }
      }

      if (!conditionOfPayment) {
        System.out.println("Kode Pembayaran Anda tidak ditemukan T_T");
        System.out.print("Apakah Anda tetap ingin melakukan pembayaran? [y/n] : ");
        char conditionChar = App.sc.next().charAt(0);
        if (conditionChar == 'y') {
          conditionOfPayment = false;
        } else {
          conditionOfPayment = true;
        }
      }
    }

  }

  private static void payBill(boolean[][] bookingNPaidDone, String[][] paymentMethod, int getInputPaymentMethod,
      String[][] bookingData) {
    App.Enter(1);
    for (int i = 0; i < paymentMethod[getInputPaymentMethod - 1].length; i++) {
      System.out.printf("[ %d. %s ] ", (i + 1), paymentMethod[getInputPaymentMethod - 1][i]);
    }

    System.out.print("=> ");
    int getMethodPayment = App.sc.nextInt();
    Pay(getInputPaymentMethod, getMethodPayment, bookingNPaidDone, paymentMethod, bookingData);

  }

  private static void Pay(int getMethodPayment, int getMethodPayment2, boolean[][] bookingNPaidDone,
      String[][] paymentMethod, String[][] bookingData) {

    App.Enter(1);
    App.menuToogle("NOTA BAYAR");
    System.out.println("Pasien Antrean : " + bookingData[App.getDataLogin][9]);
    System.out.println("Kode Pembayaran : " + bookingData[App.getDataLogin][8]);
    System.out.println("Total Bayar : " + bookingData[App.getDataLogin][7]);
    String metodaPembayaran = "";
    switch (getMethodPayment) {
      case 1:
        metodaPembayaran = "Transfer Bank";
        break;
      case 2:
        metodaPembayaran = "M-Banking";
        break;
      case 3:
        metodaPembayaran = "Dompet Digital";
        break;
      case 4:
        metodaPembayaran = "Gerai";
        break;
    }
    System.out.println("Menggunakan Metoda Pembayaran : " + metodaPembayaran);
    System.out.println(metodaPembayaran + " Dengan : " + paymentMethod[getMethodPayment - 1][getMethodPayment2 - 1]);

    App.Enter(1);
    System.out.println("1. BAYAR");
    System.out.println("2. TIDAK JADI");
    System.out.print("=> ");
    int getInput = App.sc.nextInt();
    if (getInput == 1) {
      bookingNPaidDone[1][App.getDataLogin] = true;
      App.Enter(1);
      String anim = "|/-\\";
      for (int x = 0; x < 20; x++) {
        String data = "\r" + anim.charAt(x % anim.length()) + " Proses Pembayaran";
        System.out.print(data);
        try {
          Thread.sleep(100);
        } catch (Exception e) {
        }
      }
      App.Enter(1);
      System.out.println("Pembayaran Berhasil");
      bookingData[App.getDataLogin][7] = "Lunas";

    } else {
      bookingNPaidDone[1][App.getDataLogin] = false;
      System.out.println("Pembayaran Gagal");
    }
  }

  // EDIT INFORMATION AFTER LOGIN
  public static void editInformation(String[][] dataPatient, int[][] array2dDate, boolean[][] bookingNPaidDone,
      String[] infoBookingUser1, int[] infoBookingUser2) {
    String data[] = {
        "Nama", "NIK", "Tanggal Lahir", "Tempat Lahir", "Alamat", "Nomor Kontak"
    };
    if (dataPatient[App.getDataLogin][0] != null) {
      editData(dataPatient, data, array2dDate, dataPatient, bookingNPaidDone, infoBookingUser1, infoBookingUser2);
    } else {
      System.out.println("Maaf data Anda tidak dapat ditemukan T_T ");
    }
  }

  private static void editData(String[][] dataPatient, String[] data, int[][] array2dDate, String[][] bookingData,
      boolean[][] bookingNPaidDone, String[] infoBookingUser1, int[] infoBookingUser2) {
    int nameBefore = 0;
    for (int i = 0; i < dataPatient.length; i++) {
      if (infoBookingUser1[i] == null) {
        break;
      } else {
        if (infoBookingUser1[i].equals(dataPatient[i][0])) {
          nameBefore = i;
        }
      }
    }

    for (int i = 0; i < dataPatient[0].length; i++) {
      System.out.printf("Data Sebelumnya %s: %s\n", data[i], dataPatient[App.getDataLogin][i]);
      System.out.println("------------------------------------");
      System.out.printf("Ingin Mengganti %s? [y/n] => ", data[i]);
      char isChange = App.sc.next().charAt(0);
      if (isChange == 'y') {
        System.out.print("Ganti Dengan => ");
        App.sc.nextLine();
        dataPatient[App.getDataLogin][i] = App.sc.nextLine();
        App.Enter(1);
      } else {
        continue;
      }
    }

    infoBookingUser1[nameBefore] = dataPatient[App.getDataLogin][0];

    checkUserBooking(array2dDate, bookingData, dataPatient, bookingNPaidDone, infoBookingUser1, infoBookingUser2);
  }

  // LOGOUT TO OUT INTO MENU AGAIN
  public static boolean logOut() {
    boolean result = true;
    System.out.println("Yakin ingin logout? [y/n] ");
    System.out.print("=> ");
    char logOut = App.sc.next().charAt(0);
    if (logOut == 'y') {
      result = false;
      App.getDataLogin = 0;
      App.takeInputPoli = 0;
      App.login = false;
      System.out.println("<<< Berhasil Logout >>>");
      App.Enter(1);
    } else {
      result = true;
    }

    return result;
  }

}
