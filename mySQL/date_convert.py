import re
from datetime import datetime

# Mappa dei mesi in italiano abbreviati
mesi = {
    'GEN': '01',
    'FEB': '02',
    'MAR': '03',
    'APR': '04',
    'MAG': '05',
    'GIU': '06',
    'LUG': '07',
    'AGO': '08',
    'SET': '09',
    'OTT': '10',
    'NOV': '11',
    'DIC': '12'
}

def converti_data(data_str):
    # Esempio di data_str: '01-GEN-17'
    giorno, mese_str, anno = data_str.split('-')
    mese = mesi[mese_str.upper()]
    anno = '20' + anno if int(anno) < 50 else '19' + anno
    return f'{anno}-{mese}-{giorno}'

def converti_date_nel_file(percorso_input, percorso_output):
    with open(percorso_input, 'r', encoding='latin1') as f:
        contenuto = f.read()

    # Trova tutte le date nel formato '01-GEN-17'
    pattern = r"\d{2}-[A-Z]{3}-\d{2}"
    nuove_date = {}
    
    for match in re.findall(pattern, contenuto):
        if match not in nuove_date:
            nuove_date[match] = converti_data(match)
    
    # Sostituisci le vecchie date con le nuove
    for vecchia, nuova in nuove_date.items():
        contenuto = contenuto.replace(vecchia, nuova)

    with open(percorso_output, 'w', encoding='latin1') as f:
        f.write(contenuto)

# Esempio di utilizzo
converti_date_nel_file('dati_voli.txt', 'dati_voli_date.txt')

