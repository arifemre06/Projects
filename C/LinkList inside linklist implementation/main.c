#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define NCHAR 64
struct linkedlist{
    int *data;
    struct linkedlist *next;
    int fitness;
}linkedlist;
void createlinkedlists(struct linkedlist **head,int prob){
    struct linkedlist *temp=*head;
    struct linkedlist *p=(struct linkedlist *) malloc (sizeof(struct linkedlist));
    p->next=NULL;
    p->fitness=0;
    p->data=(int*)malloc(sizeof(int)*prob);
    if(temp==NULL){
        *head=p;
    }
    else{
        while(temp->next!=NULL){
            temp=temp->next;
        }
        temp->next=p;
    }
}

void placement(struct linkedlist **head,int prob){
    struct linkedlist *p=*head;
    int i;

    while(p!=NULL){
        int a=0;
        p->fitness=0;
        for(i=0;i<prob;i++) {
            p->fitness=((pow(2,((prob-1)-a)))*(p->data[i]))+p->fitness;
            a+=1;
        }
        p=p->next;
    }
}
void ranking(struct linkedlist *head,int prob,int min,struct linkedlist *head2){
    struct linkedlist *p1=head2;
    int min2=0;
    if (min == -1){
        min2=p1->fitness;
        while(p1!=NULL) {
            if (p1->fitness < min2) {
                min2 = p1->fitness;
            }
            p1 = p1->next;
        }
    }
    if(min!=-1) {
        if(p1->fitness>min) {
            min2 = p1->fitness;
        }
        while (p1 != NULL) {

            if ((p1->fitness < min2||min2==0)&&p1->fitness > min) {
                min2 = p1->fitness;

            }
            p1 = p1->next;
        }
    }
    min=min2;
    struct linkedlist *p2=head;
    while(p2!=NULL){
        if(p2->fitness==min2){

            for(int i=0;i<prob;i++){
                int b;
                int c;
                b=p2->data[i];
                c=head->data[i];
                head->data[i]=b;
                p2->data[i]=c;

            }
            int t;
            int u;
            u=head->fitness;
            t=p2->fitness;
            head->fitness=t;
            p2->fitness=u;
        }
        p2=p2->next;
    }
    if(head->next!=NULL){

        ranking(head->next,prob,min,head2);
    }
}

void printlist(struct linkedlist *head,int prob,int generation){
    struct linkedlist *p=head;
    int i;
    printf("GENERATION: %d\n",generation);
    while(p!=NULL) {
        for (i = 0; i < prob; i++) {
            if (i == prob - 1) {
                printf("%d", p->data[i]);
            } else {
                printf("%d:", p->data[i]);
            }
        }

        printf(" -> %d\n", p->fitness);

        p = p->next;
    }
}
void printbestchromosome(struct linkedlist *head,int prob){
    struct linkedlist *p=head;
    int i=0;
    printf("best chromosome found so far: ");
    for (i = 0; i < prob; i++) {
        if (i == prob - 1) {
            printf("%d -> %d\n", p->data[i],p->fitness);
        } else {
            printf("%d:", p->data[i]);
        }
    }
    }

void myfunction(int selection1,int selection2,int xover1,int xover2,int mutate,struct linkedlist** head){
    struct linkedlist *p1,*p2;
    p1=*head;
    p2=NULL;
    int gen1_1;
    int gen1_2;
    int gen2_1;
    int gen2_2;
    if(selection2>selection1) {
        for (int i = 0; i < selection2-1; i++) {

            if(selection1>=2) {
                if (i == selection1 - 2) {
                    p2 = p1->next;
                }
            }
            else{
                if (i == selection1 - 1) {
                    p2 = p1;
                }
            }
            if (p1->next != NULL) {
                p1 = p1->next;
            }

        }
    }
    if(selection1>selection2) {
        for (int i = 0; i < selection1-1; i++) {
            if(selection2>=2) {
                if (i == selection2 - 2) {
                    p2 = p1->next;
                }
            }
            else{
                if (i == selection2 - 1) {
                    p2 = p1;
                }
            }
            if (p1->next != NULL) {
                p1 = p1->next;
            }


        }
    }
    for (int i = 0; i < xover2 ; i++) {
        if(i>=xover1-1){
            gen2_2=p2->data[i];
            gen1_2=p1->data[i];
            p1->data[i]=gen2_2;
            p2->data[i]=gen1_2;

        }
    }
}
void myfunctionmutate(int mutate,struct linkedlist** headholder) {
    struct linkedlist *p1;
    p1 = *headholder;
    int gen1_2;


    while(p1!=NULL){
        for (int i = 0; i < mutate; i++) {
            if (i == mutate-1 ) {
                gen1_2 = p1->data[i];
                p1->data[i] = 1 - gen1_2;
                break;

            }
        }
        p1=p1->next;
    }
}

char *readline (FILE *fp, char **buffer);

int main(int argc,char *argv[]) {
    int prob = atoi(argv[1]);
    int pop = atoi(argv[2]);
    int gen = atoi(argv[3]);

    int xoverfirst;
    int generation=0;
    int count = 0;
    int countholder = 0;
    int holderremakepos = 0;
    int selectxover = 0;
    int linecountxover = 0;
    int linecountxover2 = 0;
    int linecountmutate = 0;
    int linecountselection = 0;
    char **mutate = malloc(1 * sizeof(*mutate));
    char **xover = malloc(1 * sizeof(*xover));
    char **selection = malloc(1 * sizeof(*selection));

    struct linkedlist *head = NULL;
    int i;
    for (i = 0; i < pop; i++) {
        createlinkedlists(&head, prob);
    }
    int y = 2;
    char *line = NULL;
    size_t idx = 0;
    FILE *fp = y > 1 ? fopen("population", "r") : stdin;
    if (!fp) {
        fprintf(stderr, "error: file open failed '%s'.\n", "population");
        return 1;
    }
    char *linexover = NULL;
    size_t idxxover = 0;
    FILE *fpxover = y > 1 ? fopen("xover", "r") : stdin;
    if (!fpxover) {
        fprintf(stderr, "error: file open failed '%s'.\n", "xover");
        return 1;
    }
    char *lineselection = NULL;
    size_t idxselection = 0;
    FILE *fpselection = y > 1 ? fopen("selection", "r") : stdin;
    if (!fpselection) {
        fprintf(stderr, "error: file open failed '%s'.\n", "selection");
        return 1;
    }
    char *linemutate = NULL;
    size_t idxmutate = 0;
    FILE *fpmutate = y > 1 ? fopen("mutate", "r") : stdin;
    if (!fpmutate) {
        fprintf(stderr, "error: file open failed '%s'.\n", "mutate");
        return 1;
    }
    struct linkedlist *p = head;

    while (readline(fp, &line)) {  /* read each line in 'fp' */
        int b = 0;
        for (int i = 0; i < strlen(line); ++i) {

            if (i % 2 == 0) {
                int a = line[i] - '0';
                *((p->data) + b) = a;

                b += 1;

            }
        }
        p = p->next;
        countholder += 1;
        line = NULL;

    }

    placement(&head, prob);
    ranking(head, prob, -1, head);
    printlist(head, prob,generation);
    printbestchromosome(head, prob);
    generation+=1;


    if (fp != stdin) fclose(fp);

    //  XOVERRR XOVERR
    //  XOVERRR XOVERR
    //  XOVERRR XOVERR
    while (readline(fpxover, &linexover)) {  /* read each line in 'fp' */

        int a = 0;
        int b = 0;
        int k = 0;
        xover = realloc(xover, ((linecountxover2 + 1) * 2) * sizeof(*xover));

        for (int i = 0; i < strlen(linexover); i++) {
            if (linexover[i] != 32 && linexover[i] != ':') {
                a += 1;
            }
            if (linexover[i] == ':') {
                xover[b] = malloc(a * sizeof(char));
                a = 0;
                b += 1;
            }
        }

        char *token = strtok(linexover, ":");

        xover[linecountxover] = token;
        if(linecountxover==0) {
            xoverfirst = atoi(token);
        }

        token = strtok(NULL, ":");
        xover[linecountxover + 1] = token;


        linecountxover += 2;
        linecountxover2 += 1;
        line = NULL;

    }


    if (fpxover != stdin) fclose(fpxover);
    //MUTATE MUTATE MUTATE
    //MUTATE MUTATE MUTATE
    //MUTATE MUTATE MUTATE
    //MUTATE MUTATE MUTATE

    while (readline(fpmutate, &linemutate)) {  /* read each line in 'fp' */

        mutate = realloc(mutate, (linecountmutate + 1) * sizeof(*mutate));
        mutate[linecountmutate] = linemutate;


        linecountmutate += 1;
        line = NULL;
    }


    if (fpmutate != stdin) fclose(fpmutate);
//BURASI SELECTION
//BURASI SELECTION
//BURASI SELECTION
    while (readline(fpselection, &lineselection)) {  /* read each line in 'fp' */

        int a = 0;
        int b = 0;
        int k = 0;
        selection = realloc(selection, ((linecountselection + 1) * (prob)) * sizeof(*selection));
        for (int i = 0; i < strlen(lineselection); i++) {
            if (lineselection[i] != 32 && lineselection[i] != ':') {
                a += 1;
            }
            if (lineselection[i] == ':') {
                selection[b] = malloc(a * sizeof(char));
                a = 0;
                b += 1;
            }
        }


        char *token;
        token = strtok(lineselection, ": ");
        selection[0] = token;
        while (token != NULL) {
            token = strtok(NULL, ": ");
            k += 1;
            selection[k] = token;


        }

            char buffer[20];
            xover[0]=itoa(xoverfirst,buffer,10);

        for (int i = 0; i < k - 1; i += 2) {

            myfunction(atoi(selection[i]), atoi(selection[i + 1]), atoi(xover[selectxover]),
                       atoi(xover[selectxover + 1]), atoi(mutate[linecountselection]), &head);
        }
        myfunctionmutate(atoi(mutate[linecountselection]), &head);

        placement(&head, prob);
        ranking(head, prob, -1, head);
        printlist(head, prob,generation);
        printbestchromosome(head, prob);
        generation+=1;
        line = NULL;
        linecountselection+=1;
        selectxover+=2;
    }

        if (fpselection != stdin) fclose(fpselection);

        return 0;
}
/* read line from 'fp' allocate *buffer NCHAR in size
 * realloc as necessary. Returns a pointer to *buffer
 * on success, NULL otherwise.
 */

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
    (*buffer)[buflen] = 0;           /* nul-terminate */

    if (buflen == 0 && ch == EOF) {  /* return NULL if nothing read */
        free (*buffer);
        *buffer = NULL;
    }

    return *buffer;
}