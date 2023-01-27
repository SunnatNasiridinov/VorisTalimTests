package com.isystem.tests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView currentQuestion, totalQuestion, quesitonText;
    RadioButton variantA, variantB, variantC, variantD;
    RadioGroup answerGroup;
    ConstraintLayout gameBack;

    private Button nextButton;
    private ArrayList<QuestionData> dataOnaTili = new ArrayList<>();
    private ArrayList<QuestionData> dataMathematics = new ArrayList<>();
    private ArrayList<QuestionData> dataEnglish = new ArrayList<>();
    private ArrayList<QuestionData> dataRussia = new ArrayList<>();
    private Bundle bundle = new Bundle();
    private QuestionManager manager;
    final int[] answerId = new int[1];
    private boolean isAnswered = false;
    private boolean isFinished = false;

    private String text,fanlar;
    private int trueAnswers = 0;
    private int falseAnswers = 0;

    public static final String TYPE_TEST = "type_test";
    public static final String ENGLISH = "english";
    public static final String MATH = "math";
    public static final String ONA_TILI = "ona_tili";
    public static final String RUS = "rus";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        loadView();
        seekBarTotal();


        Intent intent = getIntent();
        text = intent.getStringExtra(TYPE_TEST);

        if (text.equals("math")) {
            loadDataMathematics();
            manager = new QuestionManager(dataMathematics);
            gameBack.setBackgroundResource(R.drawable.mathematics_cardview_back);
        } else if (text.equals("ona_tili")) {
            loadDataOnaTili();
            manager = new QuestionManager(dataOnaTili);
            gameBack.setBackgroundResource(R.drawable.chose_cardview_background);
        } else if (text.equals("english")) {
            loadDataEnglish();
            manager = new QuestionManager(dataEnglish);
            gameBack.setBackgroundResource(R.drawable.english_card_view_back);
        } else if (text.equals("rus")) {
            loadDataRussia();
            manager = new QuestionManager(dataRussia);
            gameBack.setBackgroundResource(R.drawable.russia_card_view_back);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startQueiz();
        setOnListner();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChoseActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    private void seekBarTotal() {
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    private void setOnListner() {

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean hasPressed = variantA.isChecked() || variantB.isChecked() || variantC.isChecked() || variantD.isChecked();
                if (isFinished) {
                    Intent intent = new Intent(GameActivity.this, ResultsActivity.class);
                    intent.putExtra("trueAnswers", trueAnswers);
                    intent.putExtra("falseAnswers", falseAnswers);
                    startActivity(intent);
                    finish();
                } else {

                    if (hasPressed) {

                        if (isAnswered) {

                            if (manager.hasQuestion()) {
                                clearView();
                                startQueiz();
                                nextButton.setText("Check");
                            } else {
                                isFinished = true;
                                nextButton.setText("Result");
                            }
                            isAnswered = false;
                        } else {

                            RadioButton button = findViewById(answerGroup.getCheckedRadioButtonId());
                            String answer = button.getText().toString();
                            boolean isTrue = manager.checkAnswer(answer);
                            if (isTrue) {
                                trueAnswers++;
                                button.setBackgroundResource(R.drawable.button_back);
                            } else {
                                falseAnswers++;
                                button.setBackgroundResource(R.drawable.false_answer);
                            }
                            variantA.setEnabled(false);
                            variantB.setEnabled(false);
                            variantC.setEnabled(false);
                            variantD.setEnabled(false);
                            nextButton.setText("Next");
                            isAnswered = true;

                        }

                    } else {
                        Toast.makeText(GameActivity.this, "Javobni tanlang", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    private void loadDataOnaTili() {
        dataOnaTili.add(new QuestionData(
                "Vaqtida uxlashga harakat qilgin, yo‘qsa kun bo‘yi miyang \n" +
                        "shishib yurasan. Fe’llar tarkibidagi qo‘shimchalardan nechtasi sifat \n" +
                        "yasovchilar bilan shakldosh?\n",
                "3 tasi",
                "1 tasi",
                "2 tasi",
                "3 tasi",
                "4 tasi"
        ));
        dataOnaTili.add(new QuestionData(
                "Quyidagi so‘zlardan to‘g‘ri yozilganlarini aniqlang.\n" +
                        "1) xoin; 2) ihlos; 3) tahmin; 4) maxfiy; 5) hayolot. ",
                "1, 4",
                "2, 3, 4",
                "1, 3, 5",
                "4, 5",
                "1, 4"
        ));
        dataOnaTili.add(new QuestionData(
                "Muqimiyni ko‘rganman. Xushfe’l, dono odam edi. \n" +
                        "Muqimiy – kattakon shoir. Qo‘qonda turadi u kishi. (Oybek)\n" +
                        "Berilgan matnda nechta shaxs oti mavjud?",
                "3 ta",
                "6 ta",
                "5 ta",
                "4 ta",
                "3 ta"
        ));
        dataOnaTili.add(new QuestionData(
                "Maqsad-sabab ravishlari berilgan qatorni toping.",
                "atayin, noilojlikdan, qasddan",
                "atayin, noilojlikdan, qasddan",
                "ataylab, jo‘rttaga, maqsadida",
                "zo‘rg‘a, atayin, olg‘a",
                "tasodifan, atayin, jo‘rttaga"
        ));
        dataOnaTili.add(new QuestionData(
                "Asosi tarkibidagi undoshlarning barchasi jarangsiz \n" +
                        "bo‘lgan yasama otni toping.",
                "chaqmoq",
                "chaqnoq",
                "chaqmoq",
                "chanqoq",
                "charchoq"
        ));
        dataOnaTili.add(new QuestionData(
                "Imlo jihatidan noto‘g‘ri yozilgan birlikni aniqlang.",
                "Lazzat oshxonasi",
                "Bulung‘ur tumani hokimligi",
                "Urganch davlat universiteti",
                "Lazzat oshxonasi",
                "Quvasoy sement zavodi"
        ));
        dataOnaTili.add(new QuestionData(
                "Tobe so‘zi ham, hokim so‘zi ham yasama bo‘lgan so‘z \n" +
                        "birikmasini toping.",
                "siniq qalamdon",
                "sevinch yoshlari",
                "iliq havo",
                "siniq qalamdon",
                "yig‘lab kelmoq"
        ));
        dataOnaTili.add(new QuestionData(
                "Qaysi birliklar so‘z birikmasi deb ham, frazeologik ibora \n" +
                        "deb ham qaralishi mumkin? \n" +
                        "1) ustidan chiqmoq; 2) kalishini to‘g‘rilamoq; \n" +
                        "3) ko‘ngliga o‘tirmoq; 4) dumini bosmoq.",
                "1, 2, 4",
                "1, 2, 3",
                "2, 3, 4",
                "3, 4",
                "1, 2, 4"
        ));
        dataOnaTili.add(new QuestionData(
                "Qaysi ko‘makchi o‘zidan oldingi so‘zning chiqish \n" +
                        "kelishigida bo‘lishini talab etadi?",
                "buyon",
                "oid",
                "tomon",
                "binoan",
                "buyon"
        ));
        dataOnaTili.add(new QuestionData(
                "Bazm uyushtirilibdi.\n" +
                        "Fe’l nisbatini aniqlang.",
                "majhul nisbat",
                "majhul nisbat",
                "orttirma nisbat",
                "birgalik nisbati",
                "o‘zlik nisbati"
        ));
        dataOnaTili.add(new QuestionData(
                "“Tirikligida ko‘p gunoh qilib qo‘ygan bo‘lsa kerak, xudo \n" +
                        "yuzini ters qilib qo‘ydi”.\n" +
                        "Ushbu jumla qaysi asar qahramoni tilidan aytilgan?",
                "“Shum bola” (G‘afur G‘ulom)",
                "“Shum bola” (G‘afur G‘ulom)",
                "“Dunyoning ishlari” (O‘tkir Hoshimov)",
                "“O‘tkan kunlar” (Abdulla Qodiriy)",
                "“Kecha va kunduz” (Cho‘lpon)"
        ));
        dataOnaTili.add(new QuestionData(
                "“…ning ishi bir tomondan uloqchi otga, ikkinchi \n" +
                        "tomondan omoch tortadigan otga o‘xshaydi. Uloqchi otdek \n" +
                        "manzilga yuguradi-yu, yer haydaydigan otdek har kuni \n" +
                        "omoch tortadi”.\n" +
                        "“Dunyoning ishlari” asaridan olingan parchada \n" +
                        "tushirilgan so‘zni belgilang.",
                "gazetachi",
                "farrosh",
                "novvoy",
                "haydovchi",
                "gazetachi"
        ));
        dataOnaTili.add(new QuestionData(
                "“Qorako‘z majnun” hikoyasi uchun epigraf sifatida \n" +
                        "keltirilgan As’hobi kahf to‘g‘risidagi hadisi sharif qaysi \n" +
                        "obrazga ishora qiladi?",
                "Qorako‘z majnun",
                "Bo‘rivoy",
                "Saodat kampir",
                "Qorako‘z majnun",
                "Qumrixon"
        ));
        dataOnaTili.add(new QuestionData(
                "Ajdarni o‘ldirib, ishora bo‘ladi deb undan tasma olgan \n" +
                        "badiiy qahramon(lar) qatorini aniqlang.\n" +
                        "1) Kenja botir (“Uch og‘a-ini botirlar”);\n" +
                        "2) Kuntug‘mish (“Kuntug‘mish”);\n" +
                        "3) Rustamxon (“Rustamxon”);\n" +
                        "4) Farhod (“Farhod va Shirin”)",
                "3",
                "1, 2, 3, 4",
                "1, 3",
                "3",
                "1, 2, 3"
        ));
        dataOnaTili.add(new QuestionData(
                "Ogahiy tomonidan suvga berilgan qanday sifatlash \n" +
                        "adabiyotda yangilik hisoblanadi?",
                "suzuk",
                "obi hayvon",
                "suzuk",
                "masihiy",
                "pariro‘y"
        ));
        dataOnaTili.add(new QuestionData(
                "“…shu odam – nazokatli odam, yo bo‘lmasa, qushni \n" +
                        "arvoh urgan”.\n" +
                        "Aholining kattalari nutqidan olingan ushbu so‘zlar \n" +
                        "qaysi dostonda berilgan?",
                "“Kuntug‘mish”",
                "“Go‘ro‘g‘lining tug‘ilishi”",
                "“Alpomish”",
                "“Rustamxon”",
                "“Kuntug‘mish”"
        ));
        dataOnaTili.add(new QuestionData(
                "Ne ravo bo‘lg‘ay bukim, mehrob ichinda o‘lturub,\n" +
                        "O‘lturadur men duochini ko‘zung boqib turib. (Lutfiy) \n" +
                        "Baytda qanday she’riy san’at qo‘llangan?",
                "tajnis",
                "ishtiqoq",
                "tajnis",
                "tard-u aks",
                "ruju’"
        ));

    }

    private void loadDataRussia() {
        dataRussia.add(new QuestionData(
                "Найдите правильное употребление предлогов в и на",
                "Идти на работу, в класс",
                "Идти на работу, в класс",
                "Идти на работу, на класс",
                "Идти в работу, на класс",
                "Идти в работу, в класс"
        ));
          dataRussia.add(new QuestionData(
                "Укажите ряд существительных, с которыми следует  употребить слово моё.",
                "письмо , дерево ,окно",
                "портфель, учебник, дневник",
                "книга, парта, ручка",
                "письмо , дерево ,окно",
                "бумага, резинка, сумка"
        ));
          dataRussia.add(new QuestionData(
                "Вставьте нужное вопросительное слова. Это.….книга?",
                "чья?",
                "чей?",
                "чья?",
                "чьё?",
                "кто?"
        ));
          dataRussia.add(new QuestionData(
                "–Нет, не друг. Это мой брат.",
                "Это твоё друг?",
                "Это твой сестра?",
                "Это твой отец?",
                "Это твой врат?",
                "Это твоё друг?"
        ));
          dataRussia.add(new QuestionData(
                "Укажите ошибку.",
                "твоя  дедушка",
                "твоя  дедушка",
                "твой  брат",
                "твоё письмо",
                "твоя сестра"
        ));
          dataRussia.add(new QuestionData(
                "Какие слова отвечают на вопрос кто?",
                "ученик, брат",
                "вишня, урюк",
                "Комната, стол",
                "ученик, брат",
                "яблоня, тутовник"
        ));
          dataRussia.add(new QuestionData(
                "Гласный звук",
                "(и)",
                "(Р)",
                "(и)",
                "(й)",
                "(п)"
        ));
          dataRussia.add(new QuestionData(
                "Вставьте пропущенный вопрос .\n" +
                        "_..........? меня зовут Анвар.",
                "Как тебя зовут?",
                "Как твоя фамилия?",
                "Это Анвар?",
                "Это твой брат?",
                "Как тебя зовут?"
        ));
     dataRussia.add(new QuestionData(
                "Какой приветствие не следует употреблять при встречи со старшими",
                "Привет",
                "Доброе утро",
                "Добрый  день",
                "Привет",
                "Здравствуйте!"
        ));
     dataRussia.add(new QuestionData(
                "Вставьте пропущенное слова.\n" +
                        "-Мама, познакомьтесь. Это…….друг\n" +
                        "Антон.\n" +
                        "-Очень приятно.",
                "мой",
                "моя",
                "мой",
                "мои",
                "твоё"
        ));
   dataRussia.add(new QuestionData(
                "Укажите слова женского рода.",
                "подруга",
                "подруга",
                "Дедушка",
                "Федя",
                "Дядя"
        ));
   dataRussia.add(new QuestionData(
                "Какое слова можно заменить местоимением оно?",
                "окно",
                "окно",
                "парта",
                "класс",
                "доска"
        ));
   dataRussia.add(new QuestionData(
                "Скажите что это  ручка принадлежит   вашему собеседнику\n" +
                        "Это ручка не моя, а……",
                "Твоя",
                "Твой",
                "моя",
                "мне",
                "Твоя"
        ));
   dataRussia.add(new QuestionData(
                "Укажите какие вещи находятся в книжномшкафу",
                "книги,журнал",
                "Куртки,портфели",
                "книги,журнал",
                "Портреты,картины",
                "чайники, стакан"
        ));
   dataRussia.add(new QuestionData(
                "Какие словане относятся к теме «Цирк»",
                "солист,сцена",
                "акробат,дрессировщик",
                "иллюзионист,фокусник",
                "солист,сцена",
                "клоун,жонглёр"
        ));
   dataRussia.add(new QuestionData(
                "Укажите ряд, где даны слова названия детёнышей зверей во множественном числе",
                "зайчата,козлята",
                "котёнок,  собачка",
                "верблюжонок,  козлёнок",
                "котёнок,  собачка",
                "зайчата,козлята"
        ));
   dataRussia.add(new QuestionData(
                "Укажите ряд где дан слова названия детёнышей зверей в единственном числе.",
                "телёнок, козлёнок",
                "утята, гусята",
                "телёнок, козлёнок",
                "зайчата, волчата",
                "лисята, львята"
        ));
   dataRussia.add(new QuestionData(
                "Одушевленное имя   существительное",
                "лошадь",
                "лошадь",
                "небо",
                "степь",
                "солнце"
        ));
   dataRussia.add(new QuestionData(
                "Существительное мужского рода",
                "год",
                "год",
                "степь",
                "речь",
                "вещь"
        ));
   dataRussia.add(new QuestionData(
                "Существительное  среднего рода",
                "здоровье",
                "заря",
                "здоровье",
                "тетрадь",
                "словарь"
        ));

    }

    private void loadDataEnglish() {
        dataEnglish.add(new QuestionData(
                "Choose the correct answer.\n" +
                        "I bought a new computer last week, but it . . .\n" +
                        "so I took it back to the shop.",
                "didn’t work",
                "worked",
                "didn’t work",
                "hasn’t worked",
                "doesn’t work"
        ));
        dataEnglish.add(new QuestionData(
                "I am accustomed to . . . care of myself.",
                "taking",
                "taken",
                "to take",
                "have taken",
                "taking"
        ));
        dataEnglish.add(new QuestionData(
                "The woman laughed . . . .",
                "loudly",
                "loudest",
                "louder",
                "loudly",
                "more loud"
        ));
        dataEnglish.add(new QuestionData(
                "He has spent . . . thousand dollars on this plan.",
                "a",
                "the",
                "a",
                " -",
                "an"
        ));
        dataEnglish.add(new QuestionData(
                "Is there any difference between . . . English\n" +
                        "spoken in the United Kingdom and that of\n" +
                        "India?\n" +
                        "− Of course, there is: in vocabulary,\n" +
                        "pronunciation and . . . meaning of some words",
                "the/the",
                "−/an",
                "the/the",
                "the/−",
                "an/the"
        ));
        dataEnglish.add(new QuestionData(
                "A brief outline of the course and bibliography\n" +
                        "were handed . . . to the students at the first\n" +
                        "meeting.",
                "out",
                "for",
                "about",
                "on",
                "out"
        ));
        dataEnglish.add(new QuestionData(
                "The noise of the children . . . us the whole\n" +
                        "morning.",
                "disturbed",
                "reduced",
                "disturbed",
                "suffered",
                "depended"
        ));
        dataEnglish.add(new QuestionData(
                "If it . . . necessary, we would have done it the\n" +
                        "other day.",
                "had been",
                "had been",
                "would have been",
                "has been",
                "is"
        ));
        dataEnglish.add(new QuestionData(
                "Will you give me . . . details on this case?",
                "further",
                "much far",
                "more further",
                "farther",
                "further"
        ));
        dataEnglish.add(new QuestionData(
                "− I like to attend evening lectures, and what\n" +
                        "about you, Feruza?\n" +
                        "− . . . .",
                "So do I",
                "She likes too",
                "So does she",
                "Neither do I",
                "So do I"
        ));


    }

    private void loadDataMathematics() {
        dataMathematics.add(new QuestionData(
                "Qaysi javobda 4 ta juft son bor?",
                "10 2 34 58",
                "3 9 6 5",
                "33 3 13 23",
                "1 9 6 3",
                "10 2 34 58"
        ));
        dataMathematics.add(new QuestionData(
                "10 ta chorak soat necha soatga teng?",
                "2,5",
                "40",
                "5,5",
                "3",
                "2,5"
        ));
        dataMathematics.add(new QuestionData(
                "3x3x3 o`lchamli kub 1x1x1 o`lchamli kubiklardan tashkil topgan. So`ng \n" +
                        "oldidan orqaga, tepadan pastga, chapdan o`ngga qarab bir qancha kubiklar \n" +
                        "rasmda ko`rsatilgandek olib tashlandi. \n" +
                        "Nechta 1x1x1 o`lchamli kubik qoldi?",
                "20",
                "15",
                "18",
                "20",
                "22"
        ));
        dataMathematics.add(new QuestionData(
                "5 ta do`st uchrashib. Har biri qolgan hammaga bittadan keks berdi. So`ng har biri o`ziga berilgan \n" +
                        "kekslarni yeb qo`ydi. Natijada ulardagi kekslar soni ikki martaga kamaydi. Boshida do`stlarda qancha\n" +
                        "keks bor edi?",
                "40",
                "40",
                "24",
                "30",
                "60"
        ));
        dataMathematics.add(new QuestionData(
                "Poygada Lotar Manfreddan oldin keldi, Viktor Jandan keyin keldi, Manfred Jandan oldin va Eddi \n" +
                        "Viktordan oldin keldi. Shu beshta poygachidan kim oxirgi bo`lib keldi?",
                "Viktor",
                "Viktor",
                "Manfred",
                "Lotar",
                "Eddi"
        ));
        dataMathematics.add(new QuestionData(
                "Julyetta o`qiyotgan kitobning barcha betlari raqamlangan. Betlarni raqamlashda ishlatilgan \n" +
                        "sonlarda beshta 0 raqami va oltita 8 raqami uchraydi. Oxirgi betning soni nechchi?",
                "58",
                "48",
                "58",
                "60",
                "68"
        ));
        dataMathematics.add(new QuestionData(
                "Andrey bir qancha olmalarni 6 ta guruhga bo`ldi. Boris ham xuddi \n" +
                        "shuncha miqdordagi olmalarni 5 ta guruhga bo`ldi. Keyin Boris o`zining \n" +
                        "har bir guruhida Andreynikiga qaraganda 2 tadan olma ko`pligiga e`tibor berdi. Andreyda qancha \n" +
                        "olma bor?",
                "60",
                "60",
                "65",
                "70",
                "75"
        ));
        dataMathematics.add(new QuestionData(
                "Alan, Bella, Kler, Dora va Erik bazmda uchrashib qolishdi va bir-birlariga qo`l berib \n" +
                        "salomlashdilar. Alan bir marta salomlashdi, Bella 2 marta qo`l berib salomlashdi, Kler 3 marta qo`l \n" +
                        "berib salomlashdi, Dora esa 4 marta qo`l berib salomlashdi. Erik necha marta qo`l berib salomlashdi?\n",
                "2",
                "1",
                "2",
                "3",
                "4"
        ));
        dataMathematics.add(new QuestionData(
                "Jeyn basketbol o`ynayapti. 20 marta to`pni otganida u 55% natija ko`rsatdi. Yana 5 marta \n" +
                        "otganidan keyin uning umumiy samarasi 56% bo`ldi. Oxirgi 5 ta otishda Jeyn necha marta to`pni \n" +
                        "savatga tushurdi?\n",
                "3",
                "1",
                "2",
                "3",
                "4"
        ));
        dataMathematics.add(new QuestionData(
                "Maykl kuchuk, sigir, mushuk va kengurularni uy hayvonlari sifatida boqadi. U Helenga 24 ta \n" +
                        "hayvoni borligini aytdi, ulardan 1/8 kuchuklar, 3/4 sigir EMAS va 2/3 mushuk EMAS. \n" +
                        "Mayklning nechta kengurusi bor?",
                "7",
                "4",
                "5",
                "6",
                "7"
        ));
        dataMathematics.add(new QuestionData(
                "Julioda ikkita turli balandlik va diametrga ega sham bor. Birinchi sham 6 soat, ikkinchisi esa 8 \n" +
                        "soat yonadi. U ikkala shamni bir vaqtda yoqdi va uch soatdan keyin shamlarning balandliklari teng \n" +
                        "bo`ldi. Shamlarning boshlang`ich balandliklari nisbatini toping?",
                "5:4",
                "4:3",
                "8:5",
                "5:4",
                "3:5"
        ));
        dataMathematics.add(new QuestionData(
                "Liam hamma pulini, donasi 1 yevro bo`lgan 50 ta gazlangan suv olish uchun ketkazdi. So`ng u \n" +
                        "gazli suvlarning ustiga bir xilda narx qo`yib, ya`ni qimmatroq sota boshladi. 40 ta gazlangan suv \n" +
                        "sotgandan keyin unda boshlangich puliga nisbatan 10 yevro ko`proq puli bor edi. Liam qolgan \n" +
                        "suvlarni ham sotib yubordi. Liamda hozir qancha pul bor?",
                "75 yevro",
                "70 yevro",
                "75 yevro",
                "80 yevro",
                "90 yevro"
        ));
        dataMathematics.add(new QuestionData(
                "1 dan n gacha bo`lgan natural sonlar bir xil oraliqda aylana bo`ylab \n" +
                        "joylashgan. 7 va 23 sonlarining orasida diametr hosil bo`ladi. n nechaga teng?",
                "32",
                "30",
                "32",
                "34",
                "36"
        ));
        dataMathematics.add(new QuestionData(
                "Natashada uzunligi 1 ga teng bo`lgan tayoqchalar bor. Tayoqchalar ko`k, \n" +
                        "qizil, sariq yoki yashil ranglarga bo`yalgan. U rasmda ko`rsatigandek 3 × 3 \n" +
                        "o`lchamli shaklni shunday yasamoqchiki, har bir 1 × 1 kvadratning 4 ta \n" +
                        "tomoni turli rang bo`lishi kerak. Natasha kamida nechta yashil tayoqcha \n" +
                        "ishlatadi?",
                "5",
                "3",
                "4",
                "5",
                "6"
        ));

        dataMathematics.add(new QuestionData(
                "Elizabetaning katta sumkasida 60 ta shokolad bor edi. Dushanba kuni u shokoladlarning 1/10 \n" +
                        "qismini yedi, seshanba kuni qolgan shokoladning 1/9 qismini, chorshanbada qolgandan 1/8 qismini, \n" +
                        "payshanba kuni qolgan shokoladning 1/7 qismini yedi va u qolayotgan shokoladning yarmini \n" +
                        "yegunicha shunday davom etdi. Elizabetada nechta shokolad qoldi?",
                "6",
                "1",
                "2",
                "4",
                "6"
        ));

        dataMathematics.add(new QuestionData(
                "Pulat diagrammadagi 8 ta aylanani qizil, sariq va ko`k rangga \n" +
                        "shunday bo`yadiki, hech qaysi to`g`ridan-to`g`ri ulangan ikkita aylana \n" +
                        "bir xil rang bo`lishi mumkin emas. Qaysi ikkita aylana bir xil rangga \n" +
                        "bo`yalishi shart?",
                "5 va 8",
                "5 va 8",
                "1 va 6",
                "2 va 7",
                "4 va 5"
        ));
        dataMathematics.add(new QuestionData(
                "Ria va Flora bir-birlarining pullarini taqqoslashganda ularning nisbati 5:3 edi. So`ng Ria 160 \n" +
                        "so`mga planshet sotib oldi. Endi ularning pullari nisbati 3:5 bo`ldi. Planshet sotib olishdan oldin \n" +
                        "Riada qancha pul bor edi?",
                "250",
                "192",
                "200",
                "250",
                "400"
        ));
        dataMathematics.add(new QuestionData(
                "3 ta o`yinchidan iborat jamoalar shaxmat turnirida ishtiroq etmoqtalar. Jamodagi har bir o`yinchi \n" +
                        "faqat bir martadan boshqa jamoaning har bir oyinchisiga qarshi o`ynay oladi. Ba`zi sabablarga ko`ra \n" +
                        "jami 250 ta o`yin o`ynalishi mumkin. O`yinda ishtirok etishi mumkin bo`lgan jamoalarning maksimal \n" +
                        "soni nechaga teng?",
                "7",
                "11",
                "7",
                "10",
                "8"
        ));
        dataMathematics.add(new QuestionData(
                "Poyezdda 18 ta vagon bor va yo`lovchilarning umumiy soni 700 ga \n" +
                        "teng. Ixtiyoriy 5 ta qo`shni vagondagi yo`lovchilarning umumiy soni 199 ga teng. Poyezdning \n" +
                        "o`rtadagi ikkita vagonida nechta yo`lovchi bor?",
                "96",
                "70",
                "77",
                "78",
                "96"
        ));

    }

    private void startQueiz() {
        quesitonText.setText(manager.getQuestion());
        variantA.setText(manager.getVariantA());
        variantB.setText(manager.getVariantB());
        variantC.setText(manager.getVariantC());
        variantD.setText(manager.getVariantD());

        currentQuestion.setText(String.valueOf(manager.getCurrentLevel()));
        totalQuestion.setText(String.valueOf(manager.getTotal()));

        seekBar.setProgress(manager.getCurrentLevel() * 100 / manager.getTotal());
    }

    private void clearView() {
        variantA.setBackgroundResource(R.drawable.answer_back);
        variantB.setBackgroundResource(R.drawable.answer_back);
        variantC.setBackgroundResource(R.drawable.answer_back);
        variantD.setBackgroundResource(R.drawable.answer_back);

        variantA.setEnabled(true);
        variantB.setEnabled(true);
        variantC.setEnabled(true);
        variantD.setEnabled(true);

        answerGroup.clearCheck();
    }




    private void loadView() {
        totalQuestion = findViewById(R.id.total_question);
        currentQuestion = findViewById(R.id.current_question);
        quesitonText = findViewById(R.id.question);
        nextButton = findViewById(R.id.next_btn);

        variantA = findViewById(R.id.answer_a);
        variantB = findViewById(R.id.answer_b);
        variantC = findViewById(R.id.answer_c);
        variantD = findViewById(R.id.answer_d);

        seekBar = findViewById(R.id.seek_bar);
        answerGroup = findViewById(R.id.answer_group);
        gameBack = findViewById(R.id.game_background);

    }


}