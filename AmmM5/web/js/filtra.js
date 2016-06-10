/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function ()
{
    
   var div = document.createElement("div");
   div.className="div_appoggio";
   document.getElementById("divTableCliente").appendChild(div);
   $(".div_appoggio").hide();
   
   $("#filtra").keyup(function()
    {  
        // Preleva il valore
        var text = $("#filtra").val();
       
        $.ajax(
        {
            url: "filter.json",
            data:{
              chiave: "q",
              valore: text
            },
            dataType: 'json',
            success : function(data, state){
                aggiornaListaOggetti(data);
            },
            error : function(data, state){
                //in caso di errore
                 console.error(state);  
            }
        });
        
       // Funzione che viene richiamata in caso di successo
        function aggiornaListaOggetti(listaOggetti)
        {
            
            $("#tableCliente").show();
            $(".dispari").remove();
            $(".pari").remove();
            $(".div_appoggio").hide();
            
            var table = document.getElementById("tableCliente");
            
            var c=0;
            var i=0;
            
            // Per ogni oggetto trovato dal database
            for(var oggetto in listaOggetti)
            {
                $(".div_appoggio").hide()
                var row = table.insertRow();
                row.className="trCliente";
                
                if (i%2==0) {
                    row.className='pari';
                }
                else {
                    row.className='dispari';
                }    
                i++;
                
                // Crea un nuovo tag td
                var td1 = row.insertCell();
                var td2 = row.insertCell();
                var td3 = row.insertCell();
                var td4 = row.insertCell();
                var td5 = row.insertCell();               
                
                
                td1.className= "tdCliente";
                td2.className= "tdCliente";
                td3.className= "tdCliente";
                td4.className= "tdCliente";
                td5.className= "tdCliente";                
                
                td1.innerHTML = listaOggetti[oggetto].nomeEAutore;
                
                // Crea immagine
                td2.innerHTML = "<img title=\""+listaOggetti[oggetto].nomeEAutore+"\" alt=\"foto del libro\" src=\""+listaOggetti[oggetto].image+"\" width=\"40\" height=\"50\">";
              
                td3.innerHTML = listaOggetti[oggetto].quantita;              
                
                td4.innerHTML = listaOggetti[oggetto].prezzo; 
                
                // Crea link
                td5.innerHTML = "<a class=\"link\" href=\"cliente.html?idOggetto="+listaOggetti[oggetto].idOggetto+"\">Compra</a>"; 
                                
                c=1;
                
                
            }
            if(c == 0){
                // nascondo la tabella 
                $("#tableCliente").hide();
                // mostro il messaggio di 'errore'
                div.innerHTML="Non c'è nessun libro corrispondente alla ricerca <i>"+text;
                $(".div_appoggio").show();
            }
        }
    }); 
});