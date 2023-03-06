#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <limits.h>
typedef struct passenger{
    char *name;
    int siranumarasi;
    int fsiranumarasi;
    int priority;
    int hasticket;
    int sellticket;
    int isstandard;
}passenger;
typedef struct queue{
    int rear;
    int front;
    struct passenger *passengers;
}queue;
void enqueue(struct queue queue2,passenger pas,int siranum){
    strcpy(queue2.passengers[queue2.front].name,pas.name);
    queue2.passengers[queue2.front].siranumarasi=siranum;
    queue2.front+=1;
};
passenger dequeue(struct queue queue2){
    return queue2.passengers[queue2.rear];
    queue2.rear+=1;
}
typedef struct flight{
    char *name;
    int businesscount;
    int economycount;
    int standartcount;
    int bqueuecount;
    int equeuecount;
    int squeuecount;
    int bsiranumara;
    int esiranumara;
    int ssiranumara;
    int fbsiranumara;
    int fesiranumara;
    int fssiranumara;
    int isclose;
    int prioritybsirasumara;
    int priorityesirasumara;
    int bselledticket;
    int eselledticket;
    int byselledticket;
    int eyselledticket;
    int sselledticket;
    int sellticketcount;
    struct passenger *bqueuepassenger;
    struct passenger *equeuepassenger;
    struct passenger *spassenger;



}flight;
void flightenqueue(char *flightname,char *class,char *passengername,struct flight *flights,int flightcount, int havepriority,FILE *fj){


    int kacinciucus=0;
    int isflightexist=0;

    for(int i=0;i<flightcount;++i){
        if(!(strcmp(flights[i].name,flightname))){
            kacinciucus=i;
            isflightexist=1;
        }
    }
    if(isflightexist==0&&(flights+(kacinciucus)) -> isclose==1){
        fprintf(fj,"error \n");
    }
    else{



        if(!(strcmp(class,"business"))){

            (flights+(kacinciucus)) -> bqueuepassenger=(struct passenger*)realloc((flights + (kacinciucus)) -> bqueuepassenger, sizeof(struct passenger) * (flights[kacinciucus].bqueuecount + 1));
            (flights+(kacinciucus)) -> bqueuepassenger[flights[kacinciucus].bqueuecount].name=calloc(strlen(passengername),
                                                                                                     sizeof(char));

            strcpy(flights[kacinciucus].bqueuepassenger[flights[kacinciucus].bqueuecount].name, passengername);
            (flights+(kacinciucus)) -> bqueuepassenger[flights[kacinciucus].bqueuecount].priority=havepriority;
            (flights+(kacinciucus)) -> bqueuepassenger[flights[kacinciucus].bqueuecount].hasticket=0;
            (flights+(kacinciucus)) -> bqueuepassenger[flights[kacinciucus].bqueuecount].isstandard=0;

            (flights + (kacinciucus))->bqueuepassenger[flights[kacinciucus].bqueuecount].siranumarasi = (
                    flights + (kacinciucus))->bsiranumara;

            (flights + (kacinciucus))->bqueuepassenger[flights[kacinciucus].bqueuecount].fsiranumarasi = (
                    flights + (kacinciucus))->fbsiranumara;

            (flights + (kacinciucus))->bsiranumara += 1;
            (flights + (kacinciucus))->fbsiranumara += 1;



            //PRINTLEME ISLEMI BURADAAAAAAAAAAA
            fprintf(fj,"queue %s %s business %d \n", (flights + (kacinciucus))->name, flights[kacinciucus].bqueuepassenger[flights[kacinciucus].bqueuecount].name, flights[kacinciucus].bqueuepassenger[flights[kacinciucus].bqueuecount].fsiranumarasi);

            (flights + (kacinciucus))->bsiranumara -= 1;
            (flights + (kacinciucus))->fbsiranumara -= 1;
            (flights + (kacinciucus))->bqueuepassenger[flights[kacinciucus].bqueuecount].siranumarasi = (
                    flights + (kacinciucus))->bsiranumara;

            (flights + (kacinciucus))->bqueuepassenger[flights[kacinciucus].bqueuecount].fsiranumarasi = (
                    flights + (kacinciucus))->fbsiranumara;


            if(((flights+(kacinciucus)) -> bqueuepassenger[flights[kacinciucus].bqueuecount].priority) == 1){
                (flights+(kacinciucus)) -> bqueuepassenger[flights[kacinciucus].bqueuecount].siranumarasi=(flights + (kacinciucus))->prioritybsirasumara;
                (flights+(kacinciucus)) -> bqueuepassenger[flights[kacinciucus].bqueuecount].fsiranumarasi=(flights + (kacinciucus))->prioritybsirasumara;
                (flights+(kacinciucus)) ->prioritybsirasumara+=1;
                (flights + (kacinciucus))->bsiranumara += 1;
                (flights + (kacinciucus))->fbsiranumara += 1;
                for(int i=0;i<flights[kacinciucus].bqueuecount;++i) {
                    if (((flights + (kacinciucus))->bqueuepassenger[i].priority) == 0) {
                        (flights + (kacinciucus))->bqueuepassenger[i].siranumarasi+=1;
                        (flights + (kacinciucus))->bqueuepassenger[i].fsiranumarasi+=1;
                    }
                }
            }
            else {
                (flights + (kacinciucus))->bqueuepassenger[flights[kacinciucus].bqueuecount].siranumarasi = (
                        flights + (kacinciucus))->bsiranumara;
                (flights + (kacinciucus))->bqueuepassenger[flights[kacinciucus].bqueuecount].fsiranumarasi = (
                        flights + (kacinciucus))->fbsiranumara;
                (flights + (kacinciucus))->bsiranumara += 1;
                (flights + (kacinciucus))->fbsiranumara += 1;
            }



            (flights+(kacinciucus)) -> bqueuecount+=1;





        }
        if(!(strcmp(class,"economy"))){

            (flights+(kacinciucus)) -> equeuepassenger=realloc((flights+(kacinciucus)) -> equeuepassenger, (flights[kacinciucus].equeuecount + 1) * sizeof(struct passenger));
            (flights+(kacinciucus)) -> equeuepassenger[flights[kacinciucus].equeuecount].name=calloc(strlen(passengername),
                                                                                                     sizeof(char));
            strcpy(flights[kacinciucus].equeuepassenger[flights[kacinciucus].equeuecount].name, passengername);
            (flights+(kacinciucus)) -> equeuepassenger[flights[kacinciucus].equeuecount].priority=havepriority;
            (flights+(kacinciucus)) -> equeuepassenger[flights[kacinciucus].equeuecount].hasticket=0;
            (flights+(kacinciucus)) -> equeuepassenger[flights[kacinciucus].equeuecount].isstandard=0;

            (flights + (kacinciucus))->equeuepassenger[flights[kacinciucus].equeuecount].siranumarasi = (
                    flights + (kacinciucus))->esiranumara;
            (flights + (kacinciucus))->equeuepassenger[flights[kacinciucus].equeuecount].fsiranumarasi = (
                    flights + (kacinciucus))->fesiranumara;
            (flights + (kacinciucus))->esiranumara += 1;
            (flights + (kacinciucus))->fesiranumara += 1;

            //PRINTLEME ISLEMI BURADAAAAAAAAAAA
            fprintf(fj,"queue %s %s economy %d \n", (flights + (kacinciucus)) -> name, flights[kacinciucus].equeuepassenger[flights[kacinciucus].equeuecount].name, (flights[kacinciucus].equeuepassenger[flights[kacinciucus].equeuecount].fsiranumarasi));

            (flights + (kacinciucus))->esiranumara -= 1;
            (flights + (kacinciucus))->fesiranumara -= 1;
            (flights + (kacinciucus))->equeuepassenger[flights[kacinciucus].equeuecount].siranumarasi = (
                    flights + (kacinciucus))->esiranumara;
            (flights + (kacinciucus))->equeuepassenger[flights[kacinciucus].equeuecount].fsiranumarasi = (
                    flights + (kacinciucus))->fesiranumara;


            if(((flights+(kacinciucus)) -> equeuepassenger[flights[kacinciucus].equeuecount].priority) == 1){
                (flights+(kacinciucus)) -> equeuepassenger[flights[kacinciucus].equeuecount].siranumarasi=(flights + (kacinciucus))->priorityesirasumara;
                (flights+(kacinciucus)) -> equeuepassenger[flights[kacinciucus].equeuecount].fsiranumarasi=(flights + (kacinciucus))->priorityesirasumara;
                (flights+(kacinciucus)) ->priorityesirasumara+=1;
                (flights + (kacinciucus))->esiranumara += 1;
                (flights + (kacinciucus))->fesiranumara += 1;
                for(int i=0;i<flights[kacinciucus].equeuecount;++i) {
                    if (((flights + (kacinciucus))->equeuepassenger[i].priority) == 0) {
                        (flights + (kacinciucus))->equeuepassenger[i].siranumarasi+=1;
                        (flights + (kacinciucus))->equeuepassenger[i].fsiranumarasi+=1;
                    }
                }
            }
            else {
                (flights + (kacinciucus))->equeuepassenger[flights[kacinciucus].equeuecount].siranumarasi = (
                        flights + (kacinciucus))->esiranumara;
                (flights + (kacinciucus))->equeuepassenger[flights[kacinciucus].equeuecount].fsiranumarasi = (
                        flights + (kacinciucus))->fesiranumara;
                (flights + (kacinciucus))->esiranumara += 1;
                (flights + (kacinciucus))->fesiranumara += 1;
            }


            (flights+(kacinciucus)) -> equeuecount+=1;




        }
        if(!(strcmp(class,"standard"))){

            (flights+(kacinciucus)) -> spassenger=realloc(flights[kacinciucus].spassenger,(flights[kacinciucus].squeuecount+1)* sizeof(struct passenger));
            (flights+(kacinciucus)) -> spassenger[flights[kacinciucus].squeuecount].name=calloc(strlen(passengername),
                                                                                                sizeof(char));

            strcpy(flights[kacinciucus].spassenger[flights[kacinciucus].squeuecount].name,passengername);
            (flights+(kacinciucus)) -> spassenger[flights[kacinciucus].squeuecount].priority=havepriority;
            (flights+(kacinciucus)) -> spassenger[flights[kacinciucus].squeuecount].hasticket=0;
            (flights+(kacinciucus)) -> spassenger[flights[kacinciucus].squeuecount].isstandard=0;

            (flights+(kacinciucus)) -> spassenger[flights[kacinciucus].squeuecount].siranumarasi=(flights+(kacinciucus))->ssiranumara;
            (flights+(kacinciucus)) -> spassenger[flights[kacinciucus].squeuecount].fsiranumarasi=(flights+(kacinciucus))->fssiranumara;
            (flights+(kacinciucus))->ssiranumara+=1;
            (flights + (kacinciucus))->fssiranumara += 1;


            //PRINTLEME ISLEMI BURADAAAAAAAAAAA
            fprintf(fj,"queue %s %s standard %d \n",(flights+(kacinciucus))->name,flights[kacinciucus].spassenger[flights[kacinciucus].squeuecount].name,flights[kacinciucus].spassenger[flights[kacinciucus].squeuecount].fsiranumarasi);

            (flights+(kacinciucus)) -> squeuecount+=1;




        }

    }

}
void sell(char *flightname,struct flight *flights,int flightcount,FILE *fj){
    int kacinciucus=0;
    int isflightexist=0;

    for(int i=0;i<flightcount;++i){
        if(!(strcmp(flights[i].name,flightname))){
            kacinciucus=i;
            isflightexist=1;
        }
    }
    if(isflightexist==0&&(flights+(kacinciucus)) -> isclose==1){
        fprintf(fj,"error \n");
    }

    else {

        (flights+(kacinciucus)) ->sellticketcount += 1;
        while ((flights+(kacinciucus)) ->businesscount > ((flights) + kacinciucus)->bselledticket &&
               ((flights) + kacinciucus)->bselledticket < ((flights) + kacinciucus)->bqueuecount) {
            ((flights) + kacinciucus)->bselledticket += 1;

        }
        while ((flights+(kacinciucus)) ->economycount > ((flights) + kacinciucus)->eselledticket &&
               ((flights) + kacinciucus)->eselledticket < ((flights) + kacinciucus)->equeuecount) {
            ((flights) + kacinciucus)->eselledticket += 1;
        }
        while ((flights+(kacinciucus)) ->standartcount > ((flights) + kacinciucus)->sselledticket &&
               ((flights) + kacinciucus)->sselledticket < ((flights) + kacinciucus)->squeuecount) {
            ((flights) + kacinciucus)->sselledticket += 1;
        }
        while ((((flights) + kacinciucus)->bselledticket) + (((flights) + kacinciucus)->byselledticket) <
               ((flights) + kacinciucus)->bqueuecount &&
               (flights+(kacinciucus)) ->standartcount > (flights[kacinciucus].sselledticket)) {
            ((flights) + kacinciucus)->sselledticket += 1;
            ((flights) + kacinciucus)->byselledticket += 1;

        }
        while (((flights+(kacinciucus)) ->eselledticket) + (((flights) + kacinciucus)->eyselledticket) <
               ((flights) + kacinciucus)->equeuecount &&
               (flights+(kacinciucus)) ->standartcount > ((flights+(kacinciucus)) ->sselledticket)) {
            ((flights) + kacinciucus)->sselledticket += 1;
            ((flights) + kacinciucus)->eyselledticket += 1;
        }

        for (int j = 0; j < ((flights) + kacinciucus)->bqueuecount; ++j) {
            //printf("%d b siranumarasi \n",((flights)+kacinciucus) ->bqueuepassenger[j].siranumarasi);
            //printf("%d %d b bselled + byselled \n",flights[kacinciucus].bselledticket,flights[kacinciucus].byselledticket);
            if (((flights) + kacinciucus)->bqueuepassenger[j].siranumarasi <= (flights + (kacinciucus)) ->bselledticket) {
                if (((flights) + kacinciucus)->bqueuepassenger[j].hasticket == 0) {
                    ((flights) + kacinciucus)->bqueuepassenger[j].sellticket = (flights + (kacinciucus)) ->sellticketcount;
                }
                ((flights) + kacinciucus)->bqueuepassenger[j].hasticket = 1;

            }
            if (((flights) + kacinciucus)->bqueuepassenger[j].siranumarasi <=
                (flights+(kacinciucus)) ->byselledticket + (flights+(kacinciucus)) ->bselledticket &&
                ((flights) + kacinciucus)->bqueuepassenger[j].siranumarasi > (flights + (kacinciucus)) ->bselledticket) {
                if (((flights) + kacinciucus)->bqueuepassenger[j].hasticket == 0) {
                    ((flights) + kacinciucus)->bqueuepassenger[j].sellticket = (flights + (kacinciucus)) ->sellticketcount;
                }
                ((flights) + kacinciucus)->bqueuepassenger[j].hasticket = 1;
                ((flights) + kacinciucus)->bqueuepassenger[j].isstandard = 1;

            }
        }

        for (int j = 0; j < ((flights) + kacinciucus)->equeuecount; ++j) {
            //printf("%d %s e siranumarasi \n",((flights)+kacinciucus) ->equeuepassenger[j].siranumarasi,((flights)+kacinciucus) ->equeuepassenger[j].name);
            //printf("%d %d e bselled + byselled \n",flights[kacinciucus].eselledticket,flights[kacinciucus].eyselledticket);
            if (((flights) + kacinciucus)->equeuepassenger[j].siranumarasi <= (flights + (kacinciucus)) ->eselledticket) {
                if (((flights) + kacinciucus)->equeuepassenger[j].hasticket == 0) {
                    ((flights) + kacinciucus)->equeuepassenger[j].sellticket = (flights + (kacinciucus)) ->sellticketcount;
                }
                ((flights) + kacinciucus)->equeuepassenger[j].hasticket = 1;


            }
            if (((flights) + kacinciucus)->equeuepassenger[j].siranumarasi <=
                (flights+(kacinciucus)) ->eyselledticket + (flights+(kacinciucus)) ->eselledticket &&
                ((flights) + kacinciucus)->equeuepassenger[j].siranumarasi > (flights + (kacinciucus)) ->eselledticket) {
                if (((flights) + kacinciucus)->equeuepassenger[j].hasticket == 0) {
                    ((flights) + kacinciucus)->equeuepassenger[j].sellticket = (flights + (kacinciucus)) ->sellticketcount;
                }
                ((flights) + kacinciucus)->equeuepassenger[j].hasticket = 1;
                ((flights) + kacinciucus)->equeuepassenger[j].isstandard = 1;

            }
        }
        for (int j = 0; j < ((flights) + kacinciucus)->squeuecount; ++j) {
            //printf("%d s siranumarasi \n",((flights)+kacinciucus) ->spassenger[j].siranumarasi);
            //printf("%d b sselled  \n",flights[kacinciucus].sselledticket);
            if (((flights) + kacinciucus)->spassenger[j].siranumarasi <= (flights+(kacinciucus)) ->sselledticket) {
                if (((flights) + kacinciucus)->spassenger[j].hasticket == 0) {
                    ((flights) + kacinciucus)->spassenger[j].sellticket = (flights+(kacinciucus)) ->sellticketcount;
                }
                ((flights) + kacinciucus)->spassenger[j].hasticket = 1;


            }
        }

        fprintf(fj,"sold %s %d %d %d \n", (flights+(kacinciucus)) ->name, (flights+(kacinciucus)) ->bselledticket,
                (flights+(kacinciucus)) ->eselledticket, (flights+(kacinciucus)) ->sselledticket);
        int sfsiranumarasi = 1;
        int bfsiranumarasi = 1;
        int efsiranumarasi = 1;
        for (int j = 0; j < ((flights) + kacinciucus)->squeuecount; ++j) {
            if (((flights) + kacinciucus)->spassenger[j].hasticket == 0) {
                sfsiranumarasi += 1;
            }
        }
        for (int j = 0; j < ((flights) + kacinciucus)->squeuecount; ++j) {
            ((flights) + kacinciucus)->spassenger[j].fsiranumarasi = sfsiranumarasi;
            ((flights) + kacinciucus)->fssiranumara=sfsiranumarasi;
        }



        for (int j = 0; j < ((flights) + kacinciucus)->bqueuecount; ++j) {
            if (((flights) + kacinciucus)->bqueuepassenger[j].hasticket == 0) {
                bfsiranumarasi += 1;
            }
        }
        for (int j = 0; j < ((flights) + kacinciucus)->bqueuecount; ++j) {
            ((flights) + kacinciucus)->bqueuepassenger[j].fsiranumarasi = bfsiranumarasi;
            ((flights) + kacinciucus)->fbsiranumara=bfsiranumarasi;
        }



        for (int j = 0; j < ((flights) + kacinciucus)->equeuecount; ++j) {
            if (((flights) + kacinciucus)->equeuepassenger[j].hasticket == 0) {
                efsiranumarasi += 1;
            }
        }
        for (int j = 0; j < ((flights) + kacinciucus)->equeuecount; ++j) {
            ((flights) + kacinciucus)->equeuepassenger[j].fsiranumarasi = efsiranumarasi;
            ((flights) + kacinciucus)->fesiranumara=efsiranumarasi;
        }

    }
}
void report(char *flightname,struct flight *flights,int flightcount,FILE *fj){
    int kacinciucus=0;
    int isflightexist=0;


    for(int i=0;i<flightcount;++i){
        if(!(strcmp(flights[i].name,flightname))){
            kacinciucus=i;
            isflightexist=1;
        }
    }
    if(isflightexist==0){
        fprintf(fj,"error \n");
    }

    else {
        int a=0;
        int byticket=0;
        int b=0;
        int eyticket=0;
        int d=0;
        int syticket=0;
        int e=0;
        int f=0;
        int g=0;

        byticket=flights[kacinciucus].bselledticket;
        eyticket=flights[kacinciucus].eselledticket;
        syticket=flights[kacinciucus].sselledticket;
        fprintf(fj,"report %s \n",flights[kacinciucus].name);

        fprintf(fj,"business %d \n",flights[kacinciucus].bselledticket);

        for(int k=1;k<=flights[kacinciucus].sellticketcount;++k) {


            for (int i = 0; i < flights[kacinciucus].bqueuecount; ++i) {
                if (((flights) + kacinciucus)->bqueuepassenger[i].priority == 1 &&
                    ((flights) + kacinciucus)->bqueuepassenger[i].hasticket == 1 && a < byticket && ((flights) + kacinciucus)->bqueuepassenger[i].sellticket == k) {
                    fprintf(fj,"%s \n", ((flights) + kacinciucus)->bqueuepassenger[i].name);
                    a += 1;
                }
            }

            for (int i = 0; i < flights[kacinciucus].bqueuecount; ++i) {
                if (((flights) + kacinciucus)->bqueuepassenger[i].priority == 0 &&
                    ((flights) + kacinciucus)->bqueuepassenger[i].hasticket == 1 && a < byticket && ((flights) + kacinciucus)->bqueuepassenger[i].sellticket == k) {
                    fprintf(fj,"%s \n", ((flights) + kacinciucus)->bqueuepassenger[i].name);
                    a += 1;
                }
            }
        }

        fprintf(fj,"economy %d \n",flights[kacinciucus].eselledticket);
        for(int k=1;k<=flights[kacinciucus].sellticketcount;++k) {

            for (int i = 0; i < flights[kacinciucus].equeuecount; ++i) {
                if (((flights) + kacinciucus)->equeuepassenger[i].priority == 1 &&
                    ((flights) + kacinciucus)->equeuepassenger[i].hasticket == 1 && b < eyticket && ((flights) + kacinciucus)->equeuepassenger[i].sellticket == k) {
                    fprintf(fj,"%s \n", ((flights) + kacinciucus)->equeuepassenger[i].name);
                    b += 1;
                }
            }
            for (int i = 0; i < flights[kacinciucus].equeuecount; ++i) {
                if (((flights) + kacinciucus)->equeuepassenger[i].priority == 0 &&
                    ((flights) + kacinciucus)->equeuepassenger[i].hasticket == 1 && b < eyticket && ((flights) + kacinciucus)->equeuepassenger[i].sellticket == k) {
                    fprintf(fj,"%s \n", ((flights) + kacinciucus)->equeuepassenger[i].name);
                    b += 1;
                }
            }
        }
        fprintf(fj,"standard %d \n",flights[kacinciucus].sselledticket);
        for(int k=1;k<=flights[kacinciucus].sellticketcount;++k) {

            for (int i = 0; i < flights[kacinciucus].squeuecount; ++i) {
                if (((flights) + kacinciucus)->spassenger[i].priority == 1 &&
                    ((flights) + kacinciucus)->spassenger[i].hasticket == 1 && d < syticket && ((flights) + kacinciucus)->spassenger[i].sellticket==k) {
                    fprintf(fj,"%s \n", ((flights) + kacinciucus)->spassenger[i].name);
                    d += 1;
                }
            }
            for (int i = 0; i < flights[kacinciucus].squeuecount; ++i) {
                if (((flights) + kacinciucus)->spassenger[i].priority == 0 &&
                    ((flights) + kacinciucus)->spassenger[i].hasticket == 1 && d < syticket && ((flights) + kacinciucus)->spassenger[i].sellticket==k) {
                    fprintf(fj,"%s \n", ((flights) + kacinciucus)->spassenger[i].name);
                    d += 1;
                    e+=1;
                }
            }
            for(int i=0;i<flights[kacinciucus].bqueuecount;++i){
                if(((flights)+kacinciucus) -> bqueuepassenger[i].hasticket == 1 && e < syticket && ((flights) + kacinciucus)->bqueuepassenger[i].priority == 1 && ((flights) + kacinciucus)->bqueuepassenger[i].sellticket == k){
                    if(f<byticket){
                        f+=1;
                    }
                    else {
                        fprintf(fj,"%s \n", ((flights) + kacinciucus)->bqueuepassenger[i].name);
                        e += 1;

                    }
                }
            }
            for(int i=0;i<flights[kacinciucus].bqueuecount;++i){
                if(((flights)+kacinciucus) -> bqueuepassenger[i].hasticket == 1 && e < syticket && ((flights) + kacinciucus)->bqueuepassenger[i].priority == 0 && ((flights) + kacinciucus)->bqueuepassenger[i].sellticket == k){
                    if(f<byticket){
                        f+=1;
                    }
                    else {
                        fprintf(fj,"%s \n", ((flights) + kacinciucus)->bqueuepassenger[i].name);
                        e += 1;

                    }
                }
            }

            for(int i=0;i<flights[kacinciucus].equeuecount;++i){
                if(((flights)+kacinciucus) -> equeuepassenger[i].hasticket == 1 && e < syticket && ((flights) + kacinciucus)->equeuepassenger[i].priority == 1 && ((flights) + kacinciucus)->equeuepassenger[i].sellticket == k){

                    if(g<eyticket){
                        g+=1;
                    }
                    else {
                        fprintf(fj,"%s \n", ((flights) + kacinciucus)->equeuepassenger[i].name);
                        e += 1;
                    }
                }
            }
            for(int i=0;i<flights[kacinciucus].equeuecount;++i){
                if(((flights)+kacinciucus) -> equeuepassenger[i].hasticket == 1 && e < syticket && ((flights) + kacinciucus)->equeuepassenger[i].priority == 0 && ((flights) + kacinciucus)->equeuepassenger[i].sellticket == k){

                    if(g<eyticket){
                        g+=1;
                    }
                    else {
                        fprintf(fj,"%s \n", ((flights) + kacinciucus)->equeuepassenger[i].name);
                        e += 1;
                    }
                }
            }

        }





        fprintf(fj,"end of report %s \n",flights[kacinciucus].name);
    }
}
void close(char *flightname,struct flight *flights,int flightcount,FILE *fj){
    int kacinciucus=0;
    int isflightexist=0;
    int sellingticketcount=0;
    int nsellingticketcount=0;


    for(int i=0;i<flightcount;++i){
        if(!(strcmp(flights[i].name,flightname))){
            kacinciucus=i;
            isflightexist=1;
        }
    }
    if(isflightexist==0){
        fprintf(fj,"error \n");
    }

    else {
        flights[kacinciucus].isclose=1;

        for(int i=0;i<flights[kacinciucus].bqueuecount;++i){
            if(flights[kacinciucus].bqueuepassenger[i].hasticket == 1){
                sellingticketcount+=1;
            }
            else if(flights[kacinciucus].bqueuepassenger[i].hasticket == 0){
                nsellingticketcount+=1;
            }
        }
        for(int i=0;i<flights[kacinciucus].equeuecount;++i){
            if(flights[kacinciucus].equeuepassenger[i].hasticket == 1){
                sellingticketcount+=1;
            }
            else if(flights[kacinciucus].equeuepassenger[i].hasticket == 0){
                nsellingticketcount+=1;
            }
        }
        for(int i=0;i<flights[kacinciucus].squeuecount;++i){
            if(flights[kacinciucus].spassenger[i].hasticket==1){
                sellingticketcount+=1;
            }
            else if(flights[kacinciucus].spassenger[i].hasticket==0){
                nsellingticketcount+=1;
            }
        }
        fprintf(fj,"closed %s %d %d \n",flights[kacinciucus].name,sellingticketcount,nsellingticketcount);
    }
    for(int i=0;i<flights[kacinciucus].bqueuecount;++i){
        if(flights[kacinciucus].bqueuepassenger[i].hasticket == 0){
            fprintf(fj,"waiting %s \n",flights[kacinciucus].bqueuepassenger[i].name);
        }

    }
    for(int i=0;i<flights[kacinciucus].equeuecount;++i){
        if(flights[kacinciucus].equeuepassenger[i].hasticket == 0){
            fprintf(fj,"waiting %s \n",flights[kacinciucus].equeuepassenger[i].name);
        }

    }
    for(int i=0;i<flights[kacinciucus].squeuecount;++i){
        if(flights[kacinciucus].spassenger[i].hasticket==0){
            fprintf(fj,"waiting %s \n",flights[kacinciucus].spassenger[i].name);
        }

    }
}
void info(char *passengername,struct flight *flights,int flightcount,FILE *fj) {
    int kacinciucus=0;
    int ispassengerexist=0;



    for(int i=0;i<flightcount;++i){
        for(int j=0;j<flights[i].bqueuecount;++j){
            if(strcmp(flights[i].bqueuepassenger[j].name, passengername) == 0){
                kacinciucus=i;
                ispassengerexist=1;
            }
        }
        for(int j=0;j<flights[i].equeuecount;++j){
            if(strcmp(flights[i].equeuepassenger[j].name, passengername) == 0){
                kacinciucus=i;
                ispassengerexist=1;
            }
        }
        for(int j=0;j<flights[i].squeuecount;++j){
            if(strcmp(flights[i].spassenger[j].name,passengername)==0){
                kacinciucus=i;
                ispassengerexist=1;
            }
        }
    }
    if(ispassengerexist==0){
        fprintf(fj,"error \n");
    }

    else {
        for(int i=0;i<flights[kacinciucus].bqueuecount;++i){
            if(strcmp(flights[kacinciucus].bqueuepassenger[i].name, passengername) == 0){
                fprintf(fj,"info %s %s ", flights[kacinciucus].bqueuepassenger[i].name, flights[kacinciucus].name);

                if(flights[kacinciucus].bqueuepassenger[i].isstandard == 1 && flights[kacinciucus].bqueuepassenger[i].hasticket == 1){
                    fprintf(fj,"%s %s \n","business","standard");
                }
                else if(flights[kacinciucus].bqueuepassenger[i].isstandard != 1 && flights[kacinciucus].bqueuepassenger[i].hasticket == 1){
                    fprintf(fj,"%s %s \n","business","business");
                }
                else if(flights[kacinciucus].bqueuepassenger[i].hasticket == 0){
                    fprintf(fj,"%s %s \n","business","none");
                }
            }
        }
        for(int i=0;i<flights[kacinciucus].equeuecount;++i){
            if(strcmp(flights[kacinciucus].equeuepassenger[i].name, passengername) == 0){
                fprintf(fj,"info %s %s ", flights[kacinciucus].equeuepassenger[i].name, flights[kacinciucus].name);

                if(flights[kacinciucus].equeuepassenger[i].isstandard == 1 && flights[kacinciucus].equeuepassenger[i].hasticket == 1){
                    fprintf(fj,"%s %s \n","economy","standard");
                }
                else if(flights[kacinciucus].equeuepassenger[i].isstandard == 0 && flights[kacinciucus].equeuepassenger[i].hasticket == 1){
                    fprintf(fj,"%s %s \n","economy","business");
                }
                else if(flights[kacinciucus].equeuepassenger[i].hasticket == 0){
                    fprintf(fj,"%s %s \n","economy","none");
                }
            }
        }
        for(int i=0;i<flights[kacinciucus].squeuecount;++i){
            if(strcmp(flights[kacinciucus].spassenger[i].name,passengername)==0) {
                fprintf(fj,"info %s %s ", flights[kacinciucus].spassenger[i].name, flights[kacinciucus].name);

                if (flights[kacinciucus].spassenger[i].hasticket == 1) {
                    fprintf(fj,"%s %s \n", "standard", "standard");
                } else if (flights[kacinciucus].spassenger[i].hasticket == 0) {
                    fprintf(fj,"%s %s \n", "standard", "none");
                }
            }
        }
    }
}
int main (int argc,char **argv){

    FILE *fr = fopen(argv[1], "r");
    FILE *fj = fopen(argv[2], "w");

    struct flight *flights=(struct flight*)calloc(1, sizeof(struct flight));
    int flightcount=0;
    int c=0,a=0,d=0,e=0,f=0,space=1,wordcount,linecount;
    char **str;
    while((c = fgetc(fr)) != EOF){
        if( c!= 32 && c!=10 && space==1){

        }

        if(c==10){
            e+=1;
            f+=1;

        }
    }

    str = calloc(e , sizeof(char *));
    for (int i = 0; i < e; ++i) {
        str[i] = calloc(f , sizeof(char));
    }
    fseek(fr, 0, SEEK_SET);
    //dosyayÄ± okuyup uygun fonksiyonlarÄ± cagÄ±rma
    while((c = fgetc(fr)) != EOF) {

        if (c != 32 && c != 10) {
            str[d][a] = c;
            a += 1;
            space = 0;

        }
        if ((c == 32 || c == 10) && space == 0) {




            a = 0;
            d += 1;

        }

        if (c == 10) {



            if(!(strcmp(str[0],"addseat"))){
                int isflightexist=0;
                int seatcount=atoi(str[3]);

                if(flightcount==0) {
                    flight flight1;
                    flightcount += 1;
                    flights = realloc(flights, sizeof(struct flight) * (flightcount));
                    (flights+(flightcount-1)) -> name=calloc(strlen(str[1]), sizeof(char));
                    strcpy((flights+(flightcount-1)) -> name,str[1]);
                    (flights+(flightcount-1)) -> sellticketcount=0;
                    (flights+(flightcount-1)) -> isclose=0;

                    (flights+(flightcount-1)) -> businesscount =0;
                    (flights+(flightcount-1)) -> bqueuepassenger=(struct passenger*)calloc(1, sizeof(struct passenger));
                    (flights+(flightcount-1)) -> bqueuecount=0;
                    (flights+(flightcount-1)) -> bsiranumara=1;
                    (flights+(flightcount-1)) -> fbsiranumara=1;
                    (flights+(flightcount-1)) -> prioritybsirasumara=1;
                    (flights+(flightcount-1)) -> bselledticket=0;
                    (flights+(flightcount-1)) -> byselledticket=0;




                    (flights+(flightcount-1)) -> economycount =0;
                    (flights+(flightcount-1)) -> equeuepassenger=(struct passenger*)calloc(1, sizeof(struct passenger));
                    (flights+(flightcount-1)) -> equeuecount=0;
                    (flights+(flightcount-1)) -> esiranumara=1;
                    (flights+(flightcount-1)) -> fesiranumara=1;
                    (flights+(flightcount-1)) -> priorityesirasumara=1;
                    (flights+(flightcount-1)) -> eselledticket=0;
                    (flights+(flightcount-1)) -> eyselledticket=0;



                    (flights+(flightcount-1)) -> standartcount =0;
                    (flights+(flightcount-1)) -> spassenger=(struct passenger*)calloc(1, sizeof(struct passenger));
                    (flights+(flightcount-1)) -> squeuecount=0;
                    (flights+(flightcount-1)) -> ssiranumara=1;
                    (flights+(flightcount-1)) -> fssiranumara=1;
                    (flights+(flightcount-1)) -> sselledticket=0;


                    flight1.businesscount=0;
                    flight1.economycount=0;
                    flight1.standartcount=0;
                    if (!(strcmp(str[2], "business")) || (str[2][0]=='b'&& str[2][1]=='u' && str[2][2]=='s')) {

                        flight1.businesscount = flight1.businesscount + seatcount;
                        (flights+(flightcount-1)) -> businesscount =flight1.businesscount;
                        fprintf(fj,"addseats %s %d %d %d \n",flights[flightcount-1].name,flights[flightcount-1].businesscount,flights[flightcount-1].economycount,flights[flightcount-1].standartcount);

                    }

                    else if (!(strcmp(str[2], "economy")) || (str[2][0]=='e'&& str[2][1]=='c' && str[2][2]=='o')) {

                        flight1.economycount = flight1.economycount + seatcount;
                        (flights+(flightcount-1)) -> economycount =flight1.economycount;
                        fprintf(fj,"addseats %s %d %d %d \n",flights[flightcount-1].name,flights[flightcount-1].businesscount,flights[flightcount-1].economycount,flights[flightcount-1].standartcount);

                    }
                    else if (!(strcmp(str[2], "standard")) || (str[2][0]=='s'&& str[2][1]=='t' && str[2][2]=='a')) {

                        flight1.standartcount = flight1.standartcount + seatcount;
                        (flights+(flightcount-1)) -> standartcount =flight1.standartcount;
                        fprintf(fj,"addseats %s %d %d %d \n",flights[flightcount-1].name,flights[flightcount-1].businesscount,flights[flightcount-1].economycount,flights[flightcount-1].standartcount);

                    }

                    else {
                        fprintf(fj,"error \n");
                    }

                }
                else{
                    for(int i=0;i<flightcount;++i) {
                        if (!(strcmp(flights[i].name, str[1]))) {

                            isflightexist=1;
                        }
                    }

                    for(int i=0;i<flightcount;++i) {
                        if (!(strcmp(flights[i].name, str[1]))&&flights[i].isclose==0) {

                            if (!(strcmp(str[2], "business"))) {
                                (flights+(i)) ->businesscount = ((flights+(i)) ->businesscount) + seatcount;
                                fprintf(fj,"addseats %s %d %d %d \n", flights[i].name, flights[i].businesscount,
                                        flights[i].economycount, flights[i].standartcount);
                            } else if (!(strcmp(str[2], "economy"))) {
                                (flights+(i)) ->economycount = ((flights+(i)) ->economycount) + seatcount;
                                fprintf(fj,"addseats %s %d %d %d \n", flights[i].name, flights[i].businesscount,
                                        flights[i].economycount, flights[i].standartcount);
                            } else if (!(strcmp(str[2], "standard"))) {
                                (flights+(i)) ->standartcount = ((flights+(i)) ->standartcount) + seatcount;
                                fprintf(fj,"addseats %s %d %d %d \n", flights[i].name, flights[i].businesscount,
                                        flights[i].economycount, flights[i].standartcount);
                            } else {
                                fprintf(fj,"error \n");
                            }

                        }
                    }
                    if(isflightexist==0){
                        flight flight1;
                        flightcount += 1;
                        flights = realloc(flights, sizeof(struct flight) * (flightcount));
                        (flights+(flightcount-1)) -> name=calloc(strlen(str[1]), sizeof(char));
                        strcpy((flights+(flightcount-1)) -> name,str[1]);
                        (flights+(flightcount-1)) -> sellticketcount=0;
                        (flights+(flightcount-1)) -> isclose=0;


                        (flights+(flightcount-1)) -> bqueuecount=0;
                        strcpy((flights+(flightcount-1)) -> name,str[1]);
                        (flights+(flightcount-1)) -> businesscount =0;
                        (flights+(flightcount-1)) -> bsiranumara=1;
                        (flights+(flightcount-1)) -> fbsiranumara=1;
                        (flights+(flightcount-1)) -> prioritybsirasumara=1;
                        (flights+(flightcount-1)) -> bselledticket=0;
                        (flights+(flightcount-1)) -> byselledticket=0;



                        (flights+(flightcount-1)) -> equeuecount=0;
                        strcpy((flights+(flightcount-1)) -> name,str[1]);
                        (flights+(flightcount-1)) -> economycount =0;
                        (flights+(flightcount-1)) -> esiranumara=1;
                        (flights+(flightcount-1)) -> fesiranumara=1;
                        (flights+(flightcount-1)) -> priorityesirasumara=1;
                        (flights+(flightcount-1)) -> eselledticket=0;
                        (flights+(flightcount-1)) -> eyselledticket=0;



                        (flights+(flightcount-1)) -> squeuecount=0;
                        strcpy((flights+(flightcount-1)) -> name,str[1]);
                        (flights+(flightcount-1)) -> standartcount =0;
                        (flights+(flightcount-1)) -> ssiranumara=1;
                        (flights+(flightcount-1)) -> fssiranumara=1;
                        (flights+(flightcount-1)) -> sselledticket=0;


                        flight1.businesscount=0;
                        flight1.economycount=0;
                        flight1.standartcount=0;
                        if (!(strcmp(str[2], "business"))) {

                            flight1.businesscount = flight1.businesscount + seatcount;
                            (flights+(flightcount-1)) -> businesscount =flight1.businesscount;
                            fprintf(fj,"addseats %s %d %d %d \n",flights[flightcount-1].name,flights[flightcount-1].businesscount,flights[flightcount-1].economycount,flights[flightcount-1].standartcount);

                        } else if (!(strcmp(str[2], "economy"))) {

                            flight1.economycount = flight1.economycount + seatcount;
                            (flights+(flightcount-1)) -> economycount =flight1.economycount;
                            fprintf(fj,"addseats %s %d %d %d \n",flights[flightcount-1].name,flights[flightcount-1].businesscount,flights[flightcount-1].economycount,flights[flightcount-1].standartcount);

                        } else if (!(strcmp(str[2], "standard"))) {

                            flight1.standartcount = flight1.standartcount + seatcount;
                            (flights+(flightcount-1)) -> standartcount =flight1.standartcount;
                            fprintf(fj,"addseats %s %d %d %d \n",flights[flightcount-1].name,flights[flightcount-1].businesscount,flights[flightcount-1].economycount,flights[flightcount-1].standartcount);

                        } else {
                            fprintf(fj,"error \n");
                        }


                    }


                }


            }

            else if(!(strcmp(str[0],"enqueue"))) {
                int havepriority=0;
                if ((!(strcmp(str[4],"diplomat"))||(str[4][2]=='p'&&str[4][3]=='l'&&str[4][4]=='o'&&str[4][5]=='m'))&&!(strcmp(str[2],"business"))) {
                    havepriority=1;

                }
                if ((!(strcmp(str[4],"veteran"))||(str[4][2]=='t'&&str[4][3]=='e'&&str[4][4]=='r'&&str[4][5]=='a'))&&!(strcmp(str[2],"economy"))) {
                    havepriority=1;

                }
                flightenqueue(str[1], str[2], str[3], flights, flightcount,havepriority,fj);



            }
            else if(!(strcmp(str[0],"sell")) || (str[0][0]=='s')&&(str[0][1]=='e')&&(str[0][2]=='l')){
                sell(str[1],flights,flightcount,fj);
            }

            else if(!(strcmp(str[0],"close"))){
                close(str[1],flights,flightcount,fj);
            }

            else if(!(strcmp(str[0],"report"))){
                report(str[1],flights,flightcount,fj);
            }

            else if(!(strcmp(str[0],"info"))){
                info(str[1],flights,flightcount,fj);
            }

            else{
                fprintf(fj,"error\n");
            }




            for (int i = 0; i < d; ++i) {
                memset(str[i], 0, f);
            }
            a = 0;
            d = 0;
            space = 1;

        }
        fflush(fj);
    }

    for (int i = 0; i < e; ++i) {
        free(str[i]);
    }
    free(str);
    free(flights);
    for(int i=0;i<flightcount-1;++i){
        free(((flights)+i) -> name);
        free(((flights)+i) -> bqueuepassenger);
        for(int j=0;j<flights[i].bqueuecount;++j){
            free(((flights)+i) -> bqueuepassenger[j].name);
        }
        free(((flights)+i) -> equeuepassenger);
        for(int j=0;j<flights[i].equeuecount;++j){
            free(((flights)+i) -> equeuepassenger[j].name);
        }
        free(((flights)+i) -> spassenger);
        for(int j=0;j<flights[i].squeuecount;++j){
            free(((flights)+i) -> spassenger[j].name);
        }
    }
    fclose(fr);
    return 0;
}