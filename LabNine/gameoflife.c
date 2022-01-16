#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <omp.h>

#define for_x for (int x = 0; x < w; x++)
#define for_y for (int y = 0; y < h; y++)
#define for_xy for_x for_y

//! Show current state of grid after evolving function.
void show(void *u, int w, int h)
{
	int (*univ)[w] = u;
	printf("\033[H");
        #pragma omp parallel for_y
	for_y {
            #pragma omp parallel for_x
		for_x printf(univ[y][x] ? "\033[07m  \033[m" : "  ");
		printf("\033[E");
	}
	fflush(stdout);
}

//! Kill or birth a cell function.
void evolve(void *u, int w, int h)
{
	unsigned (*univ)[w] = u;
	unsigned new[h][w];

        #pragma omp parallel for_y for_x
	for_y for_x {
		int n = 0;
                #pragma omp parallel for
		for (int y1 = y - 1; y1 <= y + 1; y1++)
                    #pragma omp parallel for
			for (int x1 = x - 1; x1 <= x + 1; x1++)
				if (univ[(y1 + h) % h][(x1 + w) % w])
					n++;

		if (univ[y][x]) n--;
		new[y][x] = (n == 3 || (n == 2 && univ[y][x]));
	}
        #pragma omp parallel for_y for_x
	for_y for_x univ[y][x] = new[y][x];
}

//! Main game function.
void game(int w, int h)
{
	unsigned univ[h][w];
        #pragma omp parallel for_xy
	for_xy univ[y][x] = rand() < RAND_MAX / 10 ? 1 : 0;
        #pragma omp parallel while
	while (1) {
		show(univ, w, h);
		evolve(univ, w, h);
		usleep(200000);
                #pragma omp barrier
	}
}

//! Main function of the code.
int main(int c, char **v)
{
	int w = 0, h = 0;
	if (c > 1) w = atoi(v[1]);
	if (c > 2) h = atoi(v[2]);
	if (w <= 0) w = 30;
	if (h <= 0) h = 30;
	game(w, h);
}
