from __future__ import print_function
import sys


class Simple(object):
    err = ""

    @staticmethod
    def main():
        N = int(sys.stdin.readline())
        vals = list(map(int, sys.stdin.readlines()))
        print(Simple.evaluate_four_sum(N, vals))
        if Simple.err != "":
            print(Simple.err, file=sys.stderr)
        sys.exit()

    @staticmethod
    def evaluate_four_sum(N, vals):
        for i in range(0, N - 3):
            for j in range(i + 1, N - 2):
                for k in range(j + 1, N - 1):
                    for l in range(k + 1, N):
                        if vals[i] + vals[j] + vals[k] + vals[l] == 0:
                            Simple.err = "%s %s %s %s" % (i, j, k, l)
                            return True
        return False


if __name__ == '__main__':
    Simple.main()
