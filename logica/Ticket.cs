namespace TicketsSoporte.logica
{
    public class Ticket
    {
        public int intNumero;
        public string strTitulo;
        public string strDescripcion;
        public string strCategoria;
        public string strPrioridad;
        public string strEstado;
        public string strSolucion;
        public Solicitante objSolicitante;
        public Tecnico? objTecnicoAsignado;
        public List<RegistroError> lstBitacora;
        public DateTime dtmFechaCreacion;
        public DateTime? dtmFechaResolucion;
        public DateTime? dtmFechaCierre;

        public Ticket(int intNumero, string strTitulo, string strDescripcion, string strCategoria, string strPrioridad, Solicitante objSolicitante)
        {
            this.intNumero = intNumero;
            this.strTitulo = strTitulo;
            this.strDescripcion = strDescripcion;
            this.strCategoria = strCategoria;
            this.strPrioridad = strPrioridad;
            this.objSolicitante = objSolicitante;
            strEstado = "Abierto";
            strSolucion = string.Empty;
            lstBitacora = new List<RegistroError>();
            dtmFechaCreacion = DateTime.Now;
        }

        public void asignarTecnico(Tecnico objTecnico)
        {
            objTecnicoAsignado = objTecnico;
            strEstado = "Asignado";
            objTecnico.asignarTicket(this);
        }

        public void registrarError(string strTipo, string strDescripcion, string strImpacto)
        {
            lstBitacora.Add(new RegistroError(strTipo, strDescripcion, strImpacto));
        }

        public void resolver(string strSolucion)
        {
            this.strSolucion = strSolucion;
            strEstado = "Resuelto";
            dtmFechaResolucion = DateTime.Now;
        }

        public void cerrar()
        {
            strEstado = "Cerrado";
            dtmFechaCierre = DateTime.Now;
        }

        public void mostrarResumen()
        {
            Console.ForegroundColor = ConsoleColor.Cyan;
            Console.WriteLine($"--- Ticket #{intNumero} ---");
            Console.ResetColor();
            Console.WriteLine($"  Titulo: {strTitulo}");
            Console.WriteLine($"  Categoria: {strCategoria}");
            Console.WriteLine($"  Prioridad: {strPrioridad}");
            Console.WriteLine($"  Estado: {strEstado}");
            Console.WriteLine($"  Solicitante: {objSolicitante.strNombre} ({objSolicitante.strDepartamento})");
            Console.WriteLine($"  Tecnico asignado: {(objTecnicoAsignado != null ? objTecnicoAsignado.strNombre : "sin asignar")}");
        }

        public void mostrarBitacora()
        {
            Console.ForegroundColor = ConsoleColor.Yellow;
            Console.WriteLine($"--- Bitacora del ticket #{intNumero} ---");
            Console.ResetColor();

            if (lstBitacora.Count == 0)
            {
                Console.WriteLine("  No hay errores registrados en este ticket.");
                return;
            }

            foreach (RegistroError objRegistro in lstBitacora)
            {
                Console.WriteLine("  " + objRegistro.ToString());
            }
        }
    }
}
