namespace TicketsSoporte.logica
{
    public class Solicitante : Usuario
    {
        public string strDepartamento;
        public string strExtension;

        public Solicitante(string strId, string strNombre, string strEmail, string strDepartamento, string strExtension)
            : base(strId, strNombre, strEmail)
        {
            this.strDepartamento = strDepartamento;
            this.strExtension = strExtension;
        }

        public override void mostrarInformacion()
        {
            Console.ForegroundColor = ConsoleColor.Magenta;
            Console.WriteLine($"[SOLICITANTE] {strNombre} (ID: {strId})");
            Console.ResetColor();
            Console.WriteLine($"  Email: {strEmail}");
            Console.WriteLine($"  Departamento: {strDepartamento}");
            Console.WriteLine($"  Extension: {strExtension}");
        }
    }
}
