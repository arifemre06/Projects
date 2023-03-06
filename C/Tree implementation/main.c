#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>


#define NCHAR 64
typedef struct nodewiththreechild{
    int type;
    void *child1;
    void *child2;
    void *child3;
}nodewiththreechild;

typedef struct nodewithtwochild{
    int type;
    void *child1;
    void *child2;
}nodewithtwochild;

typedef struct nodewithonechild{
    int type;
    void *child1;
}nodewithonechild;

typedef struct nodewithnochild{
    char *data;
    int type;
    int probsize;
} nodewithnochild;
//  MY FUNCTION MY FUNCTION MY FUNCTION
//  MY FUNCTION MY FUNCTION MY FUNCTION
//  MY FUNCTION MY FUNCTION MY FUNCTION
//  MY FUNCTION MY FUNCTION MY FUNCTION
//  MY FUNCTION MY FUNCTION MY FUNCTION

void myfunction(void *tree,int type,int opcount,int setopcount,int relopcount,int varcount,int preopcount,char **var,char **op,char **setop,char **relop,char **preop){



    if(type==0){
            struct nodewiththreechild *p=malloc(sizeof(nodewiththreechild));

            struct nodewithonechild *p1=malloc(sizeof(struct nodewithonechild));
            p1=tree;
            (p1->child1)=p;
            p->type=1;
            printf("if (");
            myfunction(p1->child1,p->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
            printf(") {}");

    }

    else if(type==1){

            int a=rand()%2;

                if(a==0){

                    struct nodewiththreechild *p=malloc(sizeof(nodewiththreechild));
                    struct nodewithonechild *p1=malloc(sizeof(nodewithonechild));
                    struct nodewiththreechild *p2=malloc(sizeof(nodewiththreechild));

                    struct nodewiththreechild *main=malloc(sizeof(struct nodewiththreechild));
                    main=tree;
                    main->child1=p;
                    main->child2=p1;
                    main->child3=p2;
                    p->type=1;
                    p1->type=6;
                    p2->type=1;
                    printf("(");
                    myfunction(main->child1,p->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                    myfunction(main->child2,p1->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                    myfunction(main->child3,p2->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                    printf(")");


            }   //A==0;
                if(a==1){
                    //EXPI BELIRLIYORUZ

                    int b=rand()%3;

                        //EXPI BELIRLIYORUZ 3 COCUKLU GELECEKSE BU
                        if(b==0) {
                            struct nodewiththreechild *p = malloc(sizeof(nodewiththreechild));
                            struct nodewithonechild *p1=malloc(sizeof(nodewithonechild));
                            struct nodewiththreechild *p2 = malloc(sizeof(nodewiththreechild));

                            struct nodewiththreechild *main=malloc(sizeof(struct nodewiththreechild));
                            main=tree;
                            main->child1=p;
                            main->child2=p1;
                            main->child3=p2;
                            p->type=2;
                            p1->type=5;
                            p2->type=2;
                            printf("(");
                            myfunction(main->child1,p->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            myfunction(main->child2,p1->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            myfunction(main->child3,p2->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            printf(")");

                    } //B==0;
                        //EXPI BELIRLIYORUZ 2 COCUKLU GELECEKSE BU
                        if(b==1) {
                            struct nodewithtwochild *p = malloc(sizeof(nodewithtwochild));
                            struct nodewithonechild *p1=malloc(sizeof(nodewithonechild));
                            struct nodewithtwochild *p2 = malloc(sizeof(nodewithtwochild));

                            struct nodewiththreechild *main=malloc(sizeof(struct nodewiththreechild));
                            main=tree;
                            main->child1=p;
                            main->child2=p1;
                            main->child3=p2;
                            p->type=22;
                            p1->type=5;
                            p2->type=22;
                            printf("(");
                            myfunction(main->child1,p->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            myfunction(main->child2,p1->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            myfunction(main->child3,p2->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            printf(")");

                }  // B==1;
                        //EXPI BELIRLIYORUZ 1 COCUKLU GELECEKSE BU
                        if(b==2) {
                            struct nodewithonechild *p = malloc(sizeof(nodewithonechild));
                            struct nodewithonechild *p1=malloc(sizeof(nodewithonechild));
                            struct nodewithonechild *p2 = malloc(sizeof(nodewithonechild));

                            struct nodewiththreechild *main=malloc(sizeof(struct nodewiththreechild));
                            main=tree;
                            main->child1=p;
                            main->child2=p1;
                            main->child3=p2;
                            p->type=222;
                            p1->type=5;
                            p2->type=222;
                            printf("(");
                            myfunction(main->child1,p->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            myfunction(main->child2,p1->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            myfunction(main->child3,p2->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            printf(")");

                }   //B==2;


        }   //A==1;

    } //TYPE ==1;

                if(type==2){
                    int b=rand()%3;

                    //EXPI BELIRLIYORUZ 3 COCUKLU GELECEKSE BU
                         if(b==0) {
                            struct nodewiththreechild *p = malloc(sizeof(nodewiththreechild));
                            struct nodewithonechild *p1=malloc(sizeof(nodewithonechild));
                            struct nodewiththreechild *p2 = malloc(sizeof(nodewiththreechild));

                            struct nodewiththreechild *main=malloc(sizeof(struct nodewiththreechild));
                            main=tree;
                            main->child1=p;
                            main->child2=p1;
                            main->child3=p2;
                             p->type=2;
                             p1->type=3;
                             p2->type=2;
                            printf("(");
                             myfunction(main->child1,p->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                             myfunction(main->child2,p1->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                             myfunction(main->child3,p2->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            printf(")");

                    } //B==0;
                    //EXPI BELIRLIYORUZ 2 COCUKLU GELECEKSE BU
                        if(b==1) {
                            struct nodewithtwochild *p = malloc(sizeof(nodewithtwochild));
                            struct nodewithonechild *p1=malloc(sizeof(nodewithonechild));
                            struct nodewithtwochild *p2 = malloc(sizeof(nodewithtwochild));

                            struct nodewiththreechild *main=malloc(sizeof(struct nodewiththreechild));
                            main=tree;
                            main->child1=p;
                            main->child2=p1;
                            main->child3=p2;
                            p->type=22;
                            p1->type=3;
                            p2->type=22;

                            printf("(");
                            myfunction(main->child1,p->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            myfunction(main->child2,p1->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            myfunction(main->child3,p2->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            printf(")");

                    }  // B==1;
                    //EXPI BELIRLIYORUZ 1 COCUKLU GELECEKSE BU
                        if(b==2) {
                            struct nodewithonechild *p = malloc(sizeof(nodewithonechild));
                            struct nodewithonechild *p1=malloc(sizeof(nodewithonechild));
                            struct nodewithonechild *p2 = malloc(sizeof(nodewithonechild));

                            struct nodewiththreechild *main=malloc(sizeof(struct nodewiththreechild));
                            main=tree;
                            main->child1=p;
                            main->child2=p1;
                            main->child3=p2;
                            p->type=222;
                            p1->type=3;
                            p2->type=222;
                            printf("(");
                            myfunction(main->child1,p->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            myfunction(main->child2,p1->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            myfunction(main->child3,p2->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                            printf(")");

                    }   //B==2;
                }// TYPE==2;

                    if(type==22){
                        int b=rand()%3;

                        //EXPI BELIRLIYORUZ 3 COCUKLU GELECEKSE BU
                             if(b==0) {

                                struct nodewithonechild *p1=malloc(sizeof(nodewithonechild));
                                struct nodewiththreechild *p2 = malloc(sizeof(nodewiththreechild));

                                struct nodewithtwochild *main=malloc(sizeof(struct nodewithtwochild));
                                main=tree;

                                main->child1=p1;
                                main->child2=p2;

                                 p1->type=4;
                                 p2->type=2;

                                myfunction(main->child1,p1->type=4,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                                printf("(");
                                myfunction(main->child2,p2->type=2,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                                printf(")");

                        } //B==0;
                        //EXPI BELIRLIYORUZ 2 COCUKLU GELECEKSE BU
                            if(b==1) {

                                struct nodewithonechild *p1=malloc(sizeof(nodewithonechild));
                                struct nodewithtwochild *p2 = malloc(sizeof(nodewithtwochild));

                                struct nodewithtwochild *main=malloc(sizeof(struct nodewithtwochild));
                                main=tree;
                                main->child1=p1;
                                main->child2=p2;
                                p1->type=4;
                                p2->type=22;


                                myfunction(main->child1,p1->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                                printf("(");
                                myfunction(main->child2,p2->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                                printf(")");

                        }  // B==1;
                        //EXPI BELIRLIYORUZ 1 COCUKLU GELECEKSE BU
                            if(b==2) {

                                struct nodewithonechild *p1=malloc(sizeof(nodewithonechild));
                                struct nodewithonechild *p2 = malloc(sizeof(nodewithonechild));

                                struct nodewithtwochild *main=malloc(sizeof(struct nodewithtwochild));
                                main=tree;

                                main->child1=p1;
                                main->child2=p2;
                                p1->type=4;
                                p2->type=222;


                                myfunction(main->child1,p1->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                                printf("(");
                                myfunction(main->child2,p2->type,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);
                                printf(")");

                        }   //B==2;
                    }
                        if(type==222){
                            int f=rand()%varcount;
                            struct nodewithonechild *p1=malloc(sizeof(nodewithonechild));

                            struct nodewithonechild *main=malloc(sizeof(struct nodewithonechild));
                            main=tree;

                            main->child1=p1;
                            p1->type=7;
                            printf("%s",var[f]);

                        }

                if(type==3){
                    int f=rand()%opcount;
                    struct nodewithnochild *p1=malloc(sizeof(nodewithnochild));
                    struct nodewithonechild *main=malloc(sizeof(struct nodewithonechild));
                    main=tree;
                    main->child1=p1;
                    p1->type=3;
                    printf("%s",op[f]);
                }
                if(type==4){
                    int f=rand()%preopcount;
                    struct nodewithnochild *p1=malloc(sizeof(nodewithnochild));
                    struct nodewithonechild *main=malloc(sizeof(struct nodewithonechild));
                    main=tree;
                    main->child1=p1;
                    p1->type=4;
                    printf("%s",preop[f]);
                }
                if(type==5){
                    int f=rand()%relopcount;
                    struct nodewithnochild *p1=malloc(sizeof(nodewithnochild));
                    struct nodewithonechild *main=malloc(sizeof(struct nodewithonechild));
                    main=tree;
                    main->child1=p1;
                    p1->type=5;
                    printf("%s",relop[f]);
                }
                if(type==6){
                    int f=rand()%setopcount;
                    struct nodewithnochild *p1=malloc(sizeof(nodewithnochild));
                    struct nodewithonechild *main=malloc(sizeof(struct nodewithonechild));
                    main=tree;
                    main->child1=p1;
                    p1->type=6;
                    printf("%s",setop[f]);
                }
                if(type==7){
                    int f=rand()%varcount;
                    struct nodewithnochild *p1=malloc(sizeof(nodewithnochild));
                    struct nodewithonechild *main=malloc(sizeof(struct nodewithonechild));
                    main=tree;
                    main->child1=p1;
                    p1->type=7;
                    printf("%s",var[f]);
                }


}
//  MY FUNCTION MY FUNCTION MY FUNCTION
//  MY FUNCTION MY FUNCTION MY FUNCTION
//  MY FUNCTION MY FUNCTION MY FUNCTION
//  MY FUNCTION MY FUNCTION MY FUNCTION
//  MY FUNCTION MY FUNCTION MY FUNCTION
char *readline (FILE *fp, char **buffer);

int main(int argc,char *argv[]) {

    srand(time(NULL));

    char **op=malloc(sizeof(char*)*1);
    char **preop=malloc(sizeof(char*)*1);;
    char **relop=malloc(sizeof(char*)*1);;
    char **setop=malloc(sizeof(char*)*1);;
    char **var=malloc(sizeof(char*)*1);;
    int opcount=0, preopcount=0,relopcount=0,setopcount=0,varcount=0;







    int y = 2;

    char *linevar = NULL;
    size_t idx = 0;
    FILE *fbvar = y > 1 ? fopen("var", "r") : stdin;
    if (!fbvar) {
        fprintf(stderr, "error: file open failed '%s'.\n", "C:/Users/Arif Emre/Desktop/okuma/var");
        return 1;
    }
    char *linesetop = NULL;
    size_t idxxover = 0;
    FILE *fpsetop = y > 1 ? fopen("set_op", "r") : stdin;
    if (!fpsetop) {
        fprintf(stderr, "error: file open failed '%s'.\n", "xover");
        return 1;
    }
    char *linerelop = NULL;
    size_t idxselection = 0;
    FILE *fprelop = y > 1 ? fopen("rel_op", "r") : stdin;
    if (!fprelop) {
        fprintf(stderr, "error: file open failed '%s'.\n", "selection");
        return 1;
    }
    char *linepreop = NULL;
    size_t idxmutate = 0;
    FILE *fppreop = y > 1 ? fopen("pre_op", "r") : stdin;
    if (!fppreop) {
        fprintf(stderr, "error: file open failed '%s'.\n", "pre");
        return 1;
    }

    char *lineop = NULL;
    size_t idxop = 0;
    FILE *fpop = y > 1 ? fopen("op", "r") : stdin;
    if (!fpop) {
        fprintf(stderr, "error: file open failed '%s'.\n", "op");
        return 1;
    }



    while (readline(fbvar, &linevar)) {  /* read each linevar in 'fbvar' */
        var=realloc(var, sizeof(char*)*(varcount+1));
        var[varcount]=malloc(sizeof(char)*strlen(linevar));
        strcpy(var[varcount],linevar);
        varcount+=1;
        linevar = NULL;

    }




    if (fbvar != stdin) fclose(fbvar);

    //  SET OP
    while (readline(fpsetop, &linesetop)) {  /* read each linevar in 'fbvar' */
        setop=realloc(setop, sizeof(char*)*(setopcount+1));
        setop[setopcount]=malloc(sizeof(char)*strlen(linesetop));
        strcpy(setop[setopcount],linesetop);
        setopcount+=1;
        linesetop = NULL;

    }


    if (fpsetop != stdin) fclose(fpsetop);


    //PRE OP

    while (readline(fppreop, &linepreop)) {  /* read each linevar in 'fbvar' */
        preop=realloc(preop, sizeof(char*)*(preopcount+1));
        preop[preopcount]=malloc(sizeof(char)*strlen(linepreop));
        strcpy(preop[preopcount],linepreop);
        preopcount+=1;
        linepreop = NULL;
    }


    if (fppreop != stdin) fclose(fppreop);


//rel op
    while (readline(fprelop, &linerelop)) {  /* read each linevar in 'fbvar' */
        relop=realloc(relop, sizeof(char*)*(relopcount+1));
        relop[relopcount]=malloc(sizeof(char)*strlen(linerelop));
        strcpy(relop[relopcount],linerelop);
        relopcount+=1;

        linerelop = NULL;
    }

        if (fprelop != stdin) fclose(fprelop);



  // op
    while (readline(fpop, &lineop)) {  /* read each linevar in 'fbvar' */
        op=realloc(op, sizeof(char*)*(opcount+1));
        op[opcount]=malloc(sizeof(char)*strlen(lineop));
        strcpy(op[opcount],lineop);
        opcount+=1;

    lineop = NULL;
    }

    if (fpop != stdin) fclose(fpop);

    struct nodewithonechild *p=malloc(sizeof(nodewithonechild));
    myfunction(p,0,opcount,setopcount,relopcount,varcount,preopcount,var,op,setop,relop,preop);

    return 0;


    }





char *readline (FILE *fp, char **buffer)
{
    int ch;
    size_t buflen = 0, nchar = NCHAR;

    *buffer = malloc (nchar);    /* allocate buffer nchar in length */
    if (!*buffer) {
        fprintf (stderr, "readline() error: virtual memory exhausted.\n");
        return NULL;
    }

    while ((ch = fgetc(fp)) != '\n' && ch != EOF)
    {
        (*buffer)[buflen++] = ch;

        if (buflen + 1 >= nchar) {  /* realloc */
            char *tmp = realloc (*buffer, nchar * 2);
            if (!tmp) {
                fprintf (stderr, "error: realloc failed, "
                                 "returning partial buffer.\n");
                (*buffer)[buflen] = 0;
                return *buffer;
            }
            *buffer = tmp;
            nchar *= 2;
        }
    }
    (*buffer)[buflen] = 0;

    if (buflen == 0 && ch == EOF) {
        free (*buffer);
        *buffer = NULL;
    }

    return *buffer;
}