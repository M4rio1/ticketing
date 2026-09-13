namespace TicketsSoporte.logica
{
    public class GestorTickets
    {
        public List<Tecnico> lstTecnicos;
        public List<Solicitante> lstSolicitantes;
        public List<Ticket> lstTickets;

        private int intContadorTickets;

        public GestorTickets()
        {
            lstTecnicos = new List<Tecnico>();
            lstSolicitantes = new List<Solicitante>();
            lstTickets = new List<Ticket>();
            intContadorTickets = 1;
        }

        public Ticket crearTicket(string strTitulo, string strDescripcion, string strCategoria, string strPrioridad, Solicitante objSolicitante)
        {
            Ticket objTicket = new Ticket(intContadorTickets, strTitulo, strDescripcion, strCategoria, strPrioridad, objSolicitante);
            intContadorTickets++;
            lstTickets.Add(objTicket);

            Tecnico? objTecnicoElegido = buscarTecnicoConMenorCarga(strCategoria);
            if (objTecnicoElegido != null)
            {
                objTicket.asignarTecnico(objTecnicoElegido);
            }

            return objTicket;
        }

        private Tecnico? buscarTecnicoConMenorCarga(string strCategoria)
        {
            List<Tecnico> lstCandidatos = new List<Tecnico>();

            foreach (Tecnico objTecnico in lstTecnicos)
            {
                if (string.Equals(objTecnico.strEspecialidad, strCategoria, StringComparison.OrdinalIgnoreCase))
                {
                    lstCandidatos.Add(objTecnico);
                }
            }

            if (lstCandidatos.Count == 0)
            {
                foreach (Tecnico objTecnico in lstTecnicos)
                {
                    if (string.Equals(objTecnico.strEspecialidad, "General", StringComparison.OrdinalIgnoreCase))
                    {
                        lstCandidatos.Add(objTecnico);
                    }
                }
            }

            if (lstCandidatos.Count == 0)
            {
                return null;
            }

            Tecnico objMenorCarga = lstCandidatos[0];
            foreach (Tecnico objTecnico in lstCandidatos)
            {
                if (objTecnico.obtenerCargaActual() < objMenorCarga.obtenerCargaActual())
                {
                    objMenorCarga = objTecnico;
                }
            }

            return objMenorCarga;
        }

        public Ticket buscarTicket(int intNumero)
        {
            foreach (Ticket objTicket in lstTickets)
            {
                if (objTicket.intNumero == intNumero)
                {
                    return objTicket;
                }
            }

            throw new ArgumentException($"No existe un ticket con el número {intNumero}.");
        }

        public void mostrarTickets()
        {
            Console.ForegroundColor = ConsoleColor.Yellow;
            Console.WriteLine("\n=== TICKETS REGISTRADOS ===");
            Console.ResetColor();

            if (lstTickets.Count == 0)
            {
                Console.WriteLine("No hay tickets registrados.");
                return;
            }

            foreach (Ticket objTicket in lstTickets)
            {
                objTicket.mostrarResumen();
                Console.WriteLine();
            }
        }

        public void generarResumenControl()
        {
            int intAbiertos = 0;
            int intAsignados = 0;
            int intResueltos = 0;
            int intCerrados = 0;

            foreach (Ticket objTicket in lstTickets)
            {
                switch (objTicket.strEstado)
                {
                    case "Abierto":
                        intAbiertos++;
                        break;
                    case "Asignado":
                        intAsignados++;
                        break;
                    case "Resuelto":
                        intResueltos++;
                        break;
                    case "Cerrado":
                        intCerrados++;
                        break;
                }
            }

            Console.ForegroundColor = ConsoleColor.Cyan;
            Console.WriteLine("\n=== RESUMEN DE CONTROL ===");
            Console.ResetColor();
            Console.WriteLine($"  Abiertos:  {intAbiertos}");
            Console.WriteLine($"  Asignados: {intAsignados}");
            Console.WriteLine($"  Resueltos: {intResueltos}");
            Console.WriteLine($"  Cerrados:  {intCerrados}");
            Console.ForegroundColor = ConsoleColor.Green;
            Console.WriteLine($"  Total de tickets: {lstTickets.Count}");
            Console.ResetColor();
        }
    }
}
