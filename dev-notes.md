# Dev Notes

## Infomobility endpoint
```
http://www.viaggiatreno.it/infomobilita/resteasy/viaggiatreno/infomobilitaRSS/${isInfoLavori}
```

**Params**

|        Parameter | Type   | Description                                  |
|-----------------:|:-------|:---------------------------------------------|
| `isInfoMobility` | `bool` | If `true`, infomobility data wil be returned |

**Description**
Returns an HTML Snippet though defined as *RSS*, containing a list inclusive of styling and classes. Should be parse-able.

## SmartCaring
```
http://www.viaggiatreno.it/infomobilita/resteasy/news/smartcaring?commercialTrainNumber=${number}&originCode=${origin}&searchDate=${date}
```

**Params**

|        Parameter | Type   | Description                                  |
|-----------------:|:-------|:---------------------------------------------|
| `number` | `number` | Train running number |
| `origin` | `string` | Origin staiton ID |
| `date` | `string` | Date to look up data for, in format `YYYY-MM-DD` |

**Description**
Data is returned as JSON, format is a list of objects, example:
```json
[
    {
        "0": {
            "title": "INFOTRENI INTERCITY - EUROCITY",
            "link": "https://www.trenitalia.com/content/trenitalia/it/informazioni/Infomobilita/notizie-infomobilita.html",
            "pubDate": 1793660400000,
            "description": "&lt;p&gt;I treni indicati viaggiano con un ritardo superiore a 60 minuti o possono essere oggetto di variazioni.&lt;br&gt;\r\n&amp;nbsp;&lt;/p&gt;\r\n&lt;p&gt;&lt;b&gt;&lt;a rel=&quot;noopener noreferrer&quot; target=&quot;_blank&quot; href=&quot;http://www.viaggiatreno.it/infomobilitamobile/pages/cercaTreno/cercaTreno.jsp?treno=583&amp;amp;origine=S01700&amp;amp;datapartenza=1793660400000&quot;&gt;Intercity 583 Milano Centrale (6:05) - Napoli Centrale (15:45)&lt;/a&gt;&lt;/b&gt;: il treno oggi termina la corsa a Orvieto.&lt;br&gt;\r\nI passeggeri diretti a:&lt;br&gt;\r\n- Orte possono proseguire il viaggio con il treno &lt;a rel=&quot;noopener noreferrer&quot; target=&quot;_blank&quot; href=&quot;http://www.viaggiatreno.it/infomobilitamobile/pages/cercaTreno/cercaTreno.jsp?treno=4103&amp;amp;origine=S06421&amp;amp;datapartenza=1793660400000&quot;&gt;&lt;b&gt;R 4103&lt;/b&gt;&lt;/a&gt;;&lt;br&gt;\r\n- Roma Tiburtina possono proseguire il viaggio con il treno &lt;b&gt;&lt;a rel=&quot;noopener noreferrer&quot; target=&quot;_blank&quot; href=&quot;http://www.viaggiatreno.it/infomobilitamobile/pages/cercaTreno/cercaTreno.jsp?treno=584&amp;amp;origine=S03317&amp;amp;datapartenza=1793660400000&quot;&gt;IC 584&lt;/a&gt;&amp;nbsp;&lt;/b&gt;che oggi ferma anche a Roma Tiburtina;&lt;br&gt;\r\n- Latina, Formia e Aversa possono proseguire il viaggio con il treno &lt;b&gt;&lt;a rel=&quot;noopener noreferrer&quot; target=&quot;_blank&quot; href=&quot;http://www.viaggiatreno.it/infomobilitamobile/pages/cercaTreno/cercaTreno.jsp?treno=584&amp;amp;origine=S03317&amp;amp;datapartenza=1793660400000&quot;&gt;IC 584&lt;/a&gt;&lt;/b&gt; fino a Roma Termini, dove trovano ulteriore proseguimento con i primi treni utili a cura del personale di Assistenza clienti di Trenitalia;&lt;br&gt;\r\n- Napoli Centrale possono proseguire il viaggio da Roma Tiburtina con il treno &lt;a rel=&quot;noopener noreferrer&quot; href=&quot;http://www.viaggiatreno.it/infomobilitamobile/pages/cercaTreno/cercaTreno.jsp?treno=9539&amp;amp;origine=S01700&amp;amp;datapartenza=1793660400000&quot; target=&quot;_blank&quot;&gt;&lt;b&gt;FR 9539&lt;/b&gt;&lt;/a&gt;.&lt;br&gt;\r\n&amp;nbsp;&lt;/p&gt;\r\n&lt;p&gt;&lt;b&gt;&lt;u&gt;&lt;a rel=&quot;noopener noreferrer&quot; target=&quot;_blank&quot; href=&quot;http://www.viaggiatreno.it/infomobilitamobile/pages/cercaTreno/cercaTreno.jsp?treno=541&amp;amp;origine=S07113&amp;amp;datapartenza=1793660400000&quot;&gt;Intercity 541 Ancona (15:55) - Roma Termini (20:30)&lt;/a&gt;&lt;/u&gt;&lt;/b&gt;: il treno &egrave; fermo a Fabriano dalle ore 16:52 per un inconveniente a un treno di altra Impresa Ferroviaria.&lt;br&gt;\r\n&amp;nbsp;&lt;/p&gt;\r\n&lt;p&gt;&lt;b&gt;&lt;u&gt;&lt;a rel=&quot;noopener noreferrer&quot; href=&quot;http://www.viaggiatreno.it/infomobilitamobile/pages/cercaTreno/cercaTreno.jsp?treno=822&amp;amp;origine=S08409&amp;amp;datapartenza=1793660400000&quot; target=&quot;_blank&quot;&gt;Intercity 822 Napoli Centrale (17:31) - Sestri Levante (0:24)&lt;/a&gt;&lt;/u&gt;&lt;/b&gt;: il treno oggi ha origine da Roma Termini.&lt;br&gt;\r\nI passeggeri in partenza da Napoli possono utilizzare il treno &lt;b&gt;&lt;u&gt;&lt;a rel=&quot;noopener noreferrer&quot; href=&quot;http://www.viaggiatreno.it/infomobilitamobile/pages/cercaTreno/cercaTreno.jsp?treno=9434&amp;amp;origine=S09218&amp;amp;datapartenza=1793660400000&quot; target=&quot;_blank&quot;&gt;FR 9434&lt;/a&gt;&amp;nbsp;&lt;/u&gt;&lt;/b&gt;fino a Roma Termini dove trovano proseguimento con il treno &lt;b&gt;IC 822&lt;/b&gt; che ne attende l'arrivo.&lt;br&gt;\r\n&amp;nbsp;&lt;/p&gt;\r\n&lt;p&gt;&lt;b&gt;&lt;a rel=&quot;noopener noreferrer&quot; target=&quot;_blank&quot; href=&quot;http://www.viaggiatreno.it/infomobilitamobile/pages/cercaTreno/cercaTreno.jsp?treno=794&amp;amp;origine=S11781&amp;amp;datapartenza=1793574000000&quot;&gt;Intercity Notte 794 Reggio Calabria Centrale (21:41) - Torino Porta Nuova (16:25)&lt;/a&gt;&lt;/b&gt; del 2 novembre: il treno viaggia in ritardo per un inconveniente tecnico alla linea a Paola.&lt;/p&gt;\r\n",
            "trainTags": [
                "794",
                "583",
                "822",
                "541"
            ],
            "regionTags": [],
            "evidenzia": false
        }
    }
]
```

