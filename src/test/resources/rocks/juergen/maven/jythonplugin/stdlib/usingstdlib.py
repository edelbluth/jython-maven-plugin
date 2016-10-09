import re


if __name__ == '__main__':
    test_str = 'Hallo Welt!'
    m = re.findall(r'(?P<the_ls>l+)', test_str)
    print '-'.join(m)
