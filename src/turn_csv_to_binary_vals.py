#!/usr/bin/python3

# ~3.6 seconds for mnist_100
# ~390 seconds for mnist_10000
# ~2855 seconds for mnist_60000

import time
import pandas as pd

INPUT_FILENAME = 'mnist_60000.csv'
OUTPUT_FILENAME = INPUT_FILENAME[:INPUT_FILENAME.find('.csv')] + '_binary.csv'

def main():
    data = pd.read_csv(INPUT_FILENAME, header=None)
    
    for i in range(1, data.shape[1]):
        for j in range(0, data.shape[0]):
            data[i][j] = 0 if data[i][j] == 0 else 1
        if i%5 == 0:
            print('%.2f%% done' % ((i * 100) / data.shape[1]))
    
    data.to_csv(OUTPUT_FILENAME, sep=',', index=False, header=None)

if __name__ == '__main__':
    start_time = time.time()
    main()
    print("--- %s seconds ---" % (time.time() - start_time))

