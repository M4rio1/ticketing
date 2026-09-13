namespace TicketsSoporte.logica
{
    public class Tecnico : Usuario
    {
        public string strEspecialidad;
        public int intNivelExperiencia;
        public List<Ticket> lstTicketsAsignados;

        public Tecnico(string strId, string strNombre, string strEmail, string strEspecialidad, int intNivelExperiencia)
            : base(strId, strNombre, strEmail)
        {
            this.strEspecialidad = strEspecialidad;
            this.intNivelExperiencia = intNivelExperiencia;
            lstTicketsAsignados = new List<Ticket>();
        }

        public override void mostrarInformacion()
        {
            Console.ForegroundColor = ConsoleColor.Blue;
            Console.WriteLine($"[TECNICO] {strNombre} (ID: {strId})");
            Console.ResetColor();
            Console.WriteLine($"  Email: {strEmail}");
            Console.WriteLine($"  Especialidad: {strEspecialidad}");
            Console.WriteLine($"  Nivel de experiencia: {intNivelExperiencia}");
            Console.WriteLine($"  Carga actual: {obtenerCargaActual()} ticket(s) activos");
        }

        public void asignarTicket(Ticket objTicket)
        {
            lstTicketsAsignados.Add(objTicket);
        }

        public int obtenerCargaActual()
        {
            int intCarga = 0;

            foreach (Ticket objTicket in lstTicketsAsignados)
            {
                if (objTicket.strEstado != "Cerrado")
                {
                    intCarga++;
                }
            }

            return intCarga;
        }
    }
}
